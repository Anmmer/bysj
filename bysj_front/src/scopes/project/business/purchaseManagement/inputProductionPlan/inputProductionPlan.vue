<template>
  <div>
    <ta-card>
      <span slot="title">产品生产计划：{{ year }}-{{ month }}</span>
      <ta-form
        style="margin-top:10px"
        layout="horizontal"
        :formLayout="true"
        :autoFormCreate="
              (form) => {
                this.form = form;
              }
            "
      >
        <ta-form-item label="货品编号" fieldDecoratorId="id" :span="5">
          <ta-input style="width: 180px" placeholder="请输入" />
        </ta-form-item>
        <ta-form-item label="货品名称" fieldDecoratorId="name" :span="5">
          <ta-input placeholder="请输入" style="width: 180px" />
        </ta-form-item>
        <ta-form-item :span="6">
          <ta-button @click="queryPlanCondition" type="primary">
            <ta-icon type="search" />查询
          </ta-button>
          <ta-button @click="resetValue" type="primary">
            <ta-icon type="reload" />重置
          </ta-button>
        </ta-form-item>
      </ta-form>
    </ta-card>
    <ta-card>
      <ta-button @click="openModal" style="margin-bottom: 5px;" type="primary">
        <ta-icon type="plus" />录入
      </ta-button>
      <ta-form
        :autoFormCreate="
              (form) => {
                this.form2 = form;
              }
            "
      >
        <ta-table
          :columns="tableColumns"
          :dataSource="productionPlan"
          :scroll="{ y: 350 }"
          size="middle"
        >
          <span slot="isCompute" slot-scope="text">
            <ta-tag v-if="text == '是'" type="success">是</ta-tag>
            <ta-tag v-else type="danger">否</ta-tag>
          </span>
          <ta-table-edit
            slot="num"
            slot-scope="text, record"
            :editForm="form2"
            :row-editable="true"
            :rules="[{ required: true, message: '不能为空' }]"
            type="inputNumber"
          />
          <template slot="action" slot-scope="text, record">
            <ta-table-edit
              v-if="record.isCompute !== '是'"
              :editForm="form2"
              type="rowEdit"
              rowKey="inde"
              :row-editable="true"
              :dataSource="productionPlan"
              :beforeChange="fnBeforeChange"
              @change="changeData"
              @tableChange="fnTableChange"
              @rowDelete="fnRowDelete"
            />
            <span v-if="record.isCompute == '是'">已计算，无法操作</span>
          </template>
          <span slot="num">
            数量
            <ta-icon type="edit" />
          </span>
        </ta-table>
      </ta-form>
      <ta-pagination
        style="text-align: right; margin-top: 10px"
        :dataSource.sync="productionPlan"
        :params="userPageParams"
        url="plan/queryPlanInfoPage"
        ref="gridPager"
      />
      <add-plan :visible="visible" v-if="visible" @hideModal="hideModal" />
    </ta-card>
  </div>
</template>
<script>
import $api from "./api/index";
import addPlan from "./part/addPlan.vue";
const tableColumns = [
  {
    title: "产品品号",
    dataIndex: "id",
    width: "15%",
  },
  {
    title: "产品品名",
    dataIndex: "name",
    width: "15%",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "10%",
    align: "center",
  },
  {
    title: "是否已计算",
    dataIndex: "isCompute",
    width: "15%",
    align: "center",
    scopedSlots: { customRender: "isCompute" },
  },
  {
    dataIndex: "num",
    width: "10%",
    align: "center",
    slots: { title: "num" },
    scopedSlots: { customRender: "num" },
  },
  {
    title: "录入时间",
    dataIndex: "inputDate",
    width: "15%",
    align: "center",
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "17%",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "inputProductionPlan",
  components: { addPlan },
  data() {
    return {
      visible: false,
      year: 0,
      month: 0,
      productionPlan: [],
      tableColumns,
    };
  },
  mounted() {
    this.$refs.gridPager.loadData((data) => {
      //这里是查询后的回调,可以不实现
    });
  },
  methods: {
    openModal() {
      this.visible = true;
    },
    hideModal() {
      this.visible = false;
      this.$refs.gridPager.loadData((data) => {});
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form.resetFields();
      this.$refs.gridPager.loadData((data) => {});
    },
    userPageParams() {
      let planQueryVo = this.form.getFieldsValue();
      let date = new Date();
      let d = new Date(this.year, this.month, 0);
      this.year = date.getFullYear();
      this.month = date.getMonth() + 1;
      planQueryVo.startDate = this.year + "-" + this.month + "-" + "01";
      planQueryVo.endDate = this.year + "-" + this.month + "-" + d.getDate();
      //这里返回的对象是分页要一同提交的参数
      return planQueryVo;
    },
    queryPlanCondition() {
      let data = this.form.getFieldsValue();
      if (data.id !== undefined || data.name !== undefined) {
        this.$refs.gridPager.loadData((data) => {});
      }
    },
    fnBeforeChange({ newData, record, rowKey }, callback) {
      // 单元格数据保存前数据处理（newData：返回改变后的行数据、record：该行的行数据、rowKey：该行的rowKey值、callback：回调函数，可判断数据是否正确）
      // 注意，callback 必须被调用

      callback();

      // 可在此处对表格数据进行处理
      Object.assign(record, newData);
    },
    changeData({ newData, record, rowKey }) {
      //判断是否修改
      if (!$api.isObjectValueEqual(newData, record)) {
        let data = Object.assign(record, newData);
        $api.editPlanInfo(
          this.form2,
          { inde: data.inde, num: data.num },
          (result) => {
            if (result.data.message == "1") {
              this.$message.error("已计算，无法操作");
              this.$refs.gridPager.loadData();
            } else {
              this.$message.success("修改成功");
            }
          }
        );
      }
      // 将行数据修改为新值（newData：返回改变的值、record：该行的行数据、rowKey：该行的rowKey值）
      // 也可当表格编辑状态关闭后，在此处对表格数据进行处理
    },
    fnTableChange(dataSource) {
      // 将返回有editable属性的表格数据，赋给表格
      this.productionPlan = dataSource;
    },
    fnRowDelete(deleteId) {
      // 返回主键id，即rowKey的值
      $api.removePlanById({ inde: deleteId }, (result) => {
        this.$message.success("删除成功");
        this.productionPlan = this.productionPlan.filter(
          (item) => item.inde !== deleteId
        );
      });
    },
  },
};
</script>
<style scoped type="text/less">
.top-text {
  font-weight: 550;
  font-size: 20px;
  margin-left: 10px;
}
</style>