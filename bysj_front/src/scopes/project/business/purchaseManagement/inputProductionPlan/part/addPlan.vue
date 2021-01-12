<template>
  <ta-modal
    v-model="visible"
    :destroyOnClose="true"
    :closable="false"
    :footer="null"
    :maskClosable="false"
    width="1050px"
  >
    <div slot="title">
      <span>选取产成品</span>
      <ta-button
        @click="hideModalChildren"
        style="position: absolute; right: 100px; top: 12px"
        >退出</ta-button
      >
      <ta-button
        type="primary"
        @click="addPlanSubmit"
        style="position: absolute; right: 20px; top: 12px"
        >保存</ta-button
      >
    </div>
    <div style="position: relative; top: 3px">
      <ta-form
        layout="horizontal"
        :formLayout="true"
        label-width="90px"
        :autoFormCreate="
          (form) => {
            this.form = form;
          }
        "
      >
        <ta-form-item label="货品编号" fieldDecoratorId="id" :span="6">
          <ta-input style="width: 150px" />
        </ta-form-item>
        <ta-form-item label="货品名称" fieldDecoratorId="name" :span="7">
          <ta-input style="width: 150px" />
        </ta-form-item>
        <ta-form-item :span="6">
          <ta-button @click="queryWlCondition" type="primary">查询</ta-button>
          <ta-button @click="resetValue" type="primary">重置</ta-button>
        </ta-form-item>
      </ta-form>
    </div>
    <div style="width: 680px">
      <ta-table :columns="tableColumns" :dataSource="wl" :scroll="{ y: 305 }">
        <a slot="action" slot-scope="text, record" @click="setValues(record)"
          >选择</a
        >
        <span slot="isputbom" slot-scope="text">
          <ta-tag v-if="text == '是'" type="success">是</ta-tag>
          <ta-tag v-else type="danger">否</ta-tag>
        </span></ta-table
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
    <div style="position: absolute; left: 720px; top: 80px">
      <p
        style="
          margin-bottom: 40px;
          margin-top: 5px;
          margin-left: 15px;
          font-size: 15px;
        "
      >
        产成品信息:
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
          label="数量"
          fieldDecoratorId="num"
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
    width: "80px",
    align: "center",
  },
  {
    title: "已录入BOM",
    dataIndex: "isputbom",
    width: "90px",
    align: "center",
    scopedSlots: { customRender: "isputbom" },
  },
  {
    title: "分类名称",
    dataIndex: "type",
    width: "80px",
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
  name: "addPlan",
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
      if (record.isputbom == "是") {
        this.form1.setFieldsValue({
          id1: record.id,
          name1: record.name,
          unit1: record.unit,
        });
      } else {
        Modal.info({
          title: "请先录入BOM",
        });
      }
    },
    hideModalChildren() {
      this.$emit("hideModal");
    },
    addPlanSubmit() {
      let values = this.form1.getFieldsValue();
      if (values.id1 !== undefined) {
        $api.addPlanInfo(
          this.form1,
          { id: values.id1, num: values.num, isCompute: "否" },
          (result) => {
            this.$message.success("录入成功");
            this.$emit("hideModal");
          }
        );
      } else {
        this.$message.error("请选择原材料");
      }
    },
    userPageParams() {
      let wlQueryVo = this.form.getFieldsValue();
      wlQueryVo.type = "产成品";
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