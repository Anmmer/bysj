<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">已生产产成品：{{ year }}-{{ month }}</span>
      </ta-card>
    </div>
    <div style="padding: 13.5px 13.5px; width: 100%; background-color: #f0f2f5">
      <ta-card style="height: 60px">
        <div style="position: relative; top: 3px">
          <ta-form
            layout="horizontal"
            :formLayout="true"
            :autoFormCreate="
              (form) => {
                this.form = form;
              }
            "
          >
            <ta-form-item label="货品编号" fieldDecoratorId="id" :span="6">
              <ta-input style="width: 200px" />
            </ta-form-item>
            <ta-form-item label="货品名称" fieldDecoratorId="name" :span="6">
              <ta-input placeholder="" style="width: 200px" />
            </ta-form-item>
            <ta-form-item :span="6">
              <ta-button @click="queryFPCondition" type="primary"
                >查询</ta-button
              >
              <ta-button @click="resetValue" type="primary">重置</ta-button>
            </ta-form-item>
          </ta-form>
        </div>
      </ta-card>
      <div style="padding: 13.5px 0px; height: 477px">
        <ta-card>
          <div style="height: 37px; margin-top: 3px; margin-bottom: 5px">
            <ta-button @click="openModal">录入</ta-button>
          </div>
          <ta-form
            :autoFormCreate="
              (form) => {
                this.form2 = form;
              }
            "
          >
            <ta-table
              :columns="tableColumns"
              :dataSource="productPlan"
              :haveSn="true"
              :scroll="{ y: 305 }"
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
                  :dataSource="productPlan"
                  :beforeChange="fnBeforeChange"
                  @change="changeData"
                  @tableChange="fnTableChange"
                  @rowDelete="fnRowDelete"
                />
                <span v-if="record.isCompute == '是'">已计算，无法操作</span>
              </template>
              <span slot="num">数量 <ta-icon type="edit"/></span>
            </ta-table>
          </ta-form>
          <ta-pagination
            style="text-align: right; margin-top: 10px"
            :dataSource.sync="productPlan"
            :params="userPageParams"
            url="finishedProduct/queryFinishedProductInfoPage"
            ref="gridPager"
          />
          <add-finished-product
            :visible="visible"
            :year="year"
            :month="month"
            :day="day"
            v-if="visible"
            @hideModal="hideModal"
          />
        </ta-card>
      </div>
    </div>
  </div>
</template>
<script>
import $api from "./api/index";
import addFinishedProduct from "./part/addFinishedProduct.vue";
const tableColumns = [
  {
    title: "产品品号",
    dataIndex: "id",
    width: "15%",
    align: "center",
  },
  {
    title: "产品品名",
    dataIndex: "name",
    width: "15%",
    align: "center",
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
    width: "15%",
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
    width: "15%",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "finishedProduct",
  components: { addFinishedProduct },
  data() {
    return {
      visible: false,
      year: 0,
      month: 0,
      day: 0,
      num: 0,
      productPlan: [],
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
      let FinishedProductQueryVo = this.form.getFieldsValue();
      let date = new Date();
      let d = new Date(this.year, this.month, 0);
      this.year = date.getFullYear();
      this.month = date.getMonth() + 1;
      this.day = d.getDate();
      FinishedProductQueryVo.startDate =
        this.year + "-" + this.month + "-" + "01";
      FinishedProductQueryVo.endDate =
        this.year + "-" + this.month + "-" + this.day;
      //这里返回的对象是分页要一同提交的参数
      return FinishedProductQueryVo;
    },
    queryFPCondition() {
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
        $api.editFinishedProductInfo(
          this.form2,
          {
            inde: data.inde,
            num: data.num,
            id: data.id,
            startDate: this.year + "-" + this.month + "-" + "01",
            endDate: this.year + "-" + (this.month+1) + "-" + "01",
          },
          (result) => {
            if (result.data.message === 1) {
              this.$message.error("数量超过计划总数");
            } else if (result.data.message === 2) {
              this.$message.error("已计算,无法操作");
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
      this.productPlan = dataSource;
    },
    fnRowDelete(deleteId) {
      // 返回主键id，即rowKey的值
      $api.removeFinishedProductById({ inde: deleteId }, (result) => {
        this.$message.success("删除成功");
        this.productPlan = this.productPlan.filter(
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
