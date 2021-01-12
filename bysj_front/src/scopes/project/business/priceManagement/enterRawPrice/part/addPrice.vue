<template>
  <ta-modal
    v-model="visible"
    :destroyOnClose="true"
    :closable="false"
    :footer="null"
    :maskClosable="false"
    width="1000px"
  >
    <div slot="title">
      <span>选取原材料</span>
      <ta-button
        @click="hideModalChildren"
        style="position: absolute; right: 100px; top: 12px"
        >退出</ta-button
      >
      <ta-button
        type="primary"
        @click="addChildrenSubmit"
        style="position: absolute; right: 20px; top: 12px"
        >保存</ta-button
      >
    </div>
    <div style="position: relative; top: 3px">
      <ta-form
        layout="horizontal"
        :formLayout="true"
        label-width="80px"
        :autoFormCreate="
          (form) => {
            this.form = form;
          }
        "
      >
        <ta-form-item label="货品编号" fieldDecoratorId="id" :span="6">
          <ta-input style="width: 150px" />
        </ta-form-item>
        <ta-form-item label="货品名称" fieldDecoratorId="name" :span="6">
          <ta-input style="width: 150px" />
        </ta-form-item>
        <ta-form-item :span="5">
          <ta-button @click="queryWlCondition" type="primary">查询</ta-button>
          <ta-button @click="resetValue" type="primary">重置</ta-button>
        </ta-form-item>
      </ta-form>
    </div>
    <div style="width: 620px">
      <ta-table :columns="tableColumns" :dataSource="wl" :scroll="{ y: 305 }">
        <a slot="action" slot-scope="text, record" @click="setValues(record)"
          >选择</a
        ></ta-table
      >
      <ta-pagination
        size="small"
        style="text-align: right; margin-top: 15px"
        :dataSource.sync="wl"
        :params="userPageParams"
        url="basicInfo/queryWlInfoPage"
        ref="gridPagerChildren"
      />
    </div>
    <div style="position: absolute; left: 670px; top: 80px">
      <p
        style="
          margin-bottom: 40px;
          margin-top: 5px;
          margin-left: 15px;
          font-size: 15px;
        "
      >
        原材料信息:
      </p>
      <ta-form
        label-width="100px"
        :disabled="true"
        :autoFormCreate="
          (form) => {
            this.form1 = form;
          }
        "
      >
        <ta-form-item label="科目编号" fieldDecoratorId="id1" :span="13">
          <ta-input />
        </ta-form-item>
        <ta-form-item label="品名" fieldDecoratorId="name1" :span="13">
          <ta-input />
        </ta-form-item>
        <ta-form-item label="单位" fieldDecoratorId="unit1" :span="13">
          <ta-input />
        </ta-form-item>
        <ta-form-item
          label="价格"
          fieldDecoratorId="price1"
          :disabled="false"
          :require="{ message: '请输入!' }"
          :span="13"
        >
          <ta-input-number :min="1" />
        </ta-form-item>
      </ta-form>
    </div>
  </ta-modal>
</template>
<script>
import $api from "../api/index";
const tableColumns = [
  {
    title: "科目编号",
    dataIndex: "id",
    width: "80px",
    align: "center",
  },
  {
    title: "品名",
    dataIndex: "name",
    width: "130px",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "60px",
    align: "center",
  },
  {
    title: "分类名称",
    dataIndex: "type",
    width: "90px",
    align: "center",
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "90px",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "addPrice",
  props: ["visible"],
  data() {
    return {
      tableColumns,
      wl: [],
    };
  },
  mounted() {
    this.$refs.gridPagerChildren.loadData((data) => {});
  },
  methods: {
    setValues(record) {
      this.form1.setFieldsValue({
        id1: record.id,
        name1: record.name,
        unit1: record.unit,
      });
    },
    hideModalChildren() {
      this.$emit("hideModal");
    },
    addChildrenSubmit() {
      let values = this.form1.getFieldsValue();
      if (values.id1 !== undefined) {
        $api.addRawPrice(
          this.form1,
          { id: values.id1, price: values.price1, isputprice: "是" },
          (result) => {
            this.$message.success("录入成功");
            this.$emit("hideModal");
          }
        );
      }else{
        this.$message.error("请选择原材料")
      }
    },
    userPageParams() {
      let wlQueryVo = this.form.getFieldsValue();
      wlQueryVo.type = "原材料";
      wlQueryVo.isputprice = "否";
      return wlQueryVo;
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form.resetFields();
      this.$refs.gridPagerChildren.loadData((data) => {});
    },
    queryWlCondition() {
      let data = this.form.getFieldsValue();
      if (data.id !== undefined || data.name !== undefined) {
        this.$refs.gridPagerChildren.loadData((data) => {});
      }
    },
  },
};
</script>