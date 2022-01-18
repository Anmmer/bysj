<template>
  <div>
    <ta-card title="辅助材料单价录入">
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
          <ta-input placeholder="请输入" style="width: 180px"  />
        </ta-form-item>
        <ta-form-item :span="5">
          <ta-button @click="queryWLCondition" type="primary"><ta-icon type="search" />查询</ta-button>
          <ta-button @click="resetValue" type="primary"><ta-icon type="reload" />重置</ta-button>
        </ta-form-item>
      </ta-form>
    </ta-card>
    <ta-card>
      <ta-button @click="openModal" style="margin-bottom: 4px;" type="primary">
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
          :dataSource="rawMaterial"
          :scroll="{ y: 351 }"
          size="middle"
        >
          <ta-table-edit
            slot="price"
            slot-scope="text, record"
            :editForm="form2"
            :row-editable="true"
            :rules="[{ required: true, message: '不能为空' }]"
            type="inputNumber"
          />
          <ta-table-edit
            :editForm="form2"
            slot="action"
            slot-scope="text, record"
            type="rowEdit"
            rowKey="id"
            :row-editable="true"
            :dataSource="rawMaterial"
            :beforeChange="fnBeforeChange"
            @change="changeData"
            @tableChange="fnTableChange"
            @rowDelete="fnRowDelete"
          />
          <span slot="price">
            单价
            <ta-icon type="edit" />
          </span>
        </ta-table>
      </ta-form>
      <ta-pagination
        style="text-align: right; margin-top: 10px"
        :dataSource.sync="rawMaterial"
        :params="userPageParams"
        url="basicInfo/queryRawListPage"
        ref="gridPager"
      />
      <add-price :visible="visible" v-if="visible" @hideModal="hideModal" />
    </ta-card>
  </div>
</template>
<script>
import $api from "./api/index";
import addPrice from "./part/addPrice.vue";

const tableColumns = [
  {
    title: "辅助材料品号",
    dataIndex: "id",
    width: "17%",
  },
  {
    title: "辅助材料品名",
    dataIndex: "name",
    width: "17%",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "15%",
    align: "center",
  },
  {
    dataIndex: "price",
    align: "center",
    width: "15%",
    slots: { title: "price" },
    scopedSlots: { customRender: "price" },
  },
  {
    title: "录入时间",
    dataIndex: "pricedate",
    width: "20%",
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
  name: "enterAuxiliaryPrice",
  components: { addPrice },
  data() {
    return {
      visible: false,
      rawMaterial: [],
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
      let wlQueryVo = this.form.getFieldsValue();
      wlQueryVo.type = "辅助材料";
      wlQueryVo.isputprice = "是";
      //这里返回的对象是分页要一同提交的参数
      return wlQueryVo;
    },
    queryWLCondition() {
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
        $api.editBomInfo(
          this.form2,
          { id: data.id, price: data.price },
          (result) => {
            this.$message.success("修改成功");
          }
        );
      }
      // 将行数据修改为新值（newData：返回改变的值、record：该行的行数据、rowKey：该行的rowKey值）
      // 也可当表格编辑状态关闭后，在此处对表格数据进行处理
    },
    fnTableChange(dataSource) {
      // 将返回有editable属性的表格数据，赋给表格
      this.rawMaterial = dataSource;
    },
    fnRowDelete(deleteId) {
      // 返回主键id，即rowKey的值
      $api.removeRawPrice({ id: deleteId, isputprice: "否" }, (result) => {
        this.$message.success("删除成功");
        this.rawMaterial = this.rawMaterial.filter(
          (item) => item.id !== deleteId
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
