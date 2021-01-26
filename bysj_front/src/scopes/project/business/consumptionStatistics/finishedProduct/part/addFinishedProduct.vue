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
      <span>选取产成品生产计划</span>
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
        <ta-form-item label="计划日期" fieldDecoratorId="time" :span="7">
          <ta-month-picker />
        </ta-form-item>
        <ta-form-item :span="6">
          <ta-button @click="queryPlanCondition" type="primary">查询</ta-button>
          <ta-button @click="resetValue" type="primary">重置</ta-button>
        </ta-form-item>
      </ta-form>
    </div>
    <div style="width: 680px">
      <ta-table
        :columns="tableColumns"
        :dataSource="plan"
        :scroll="{ y: 305 }"
        size="middle"
      >
        <a slot="action" slot-scope="text, record" @click="setValues(record)"
          >选择</a
        >
      </ta-table>
      <ta-pagination
        size="small"
        style="text-align: right; margin-top: 15px"
        :dataSource.sync="plan"
        :params="userPageParams"
        url="finishedProduct/queryNumListPage"
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
    width: "90px",
    align: "center",
  },
  {
    title: "品名",
    dataIndex: "name",
    width: "130px",
    align: "center",
  },
  {
    title: "计划日期",
    dataIndex: "planDate",
    width: "80px",
    align: "center",
  },
  {
    title: "计划总数",
    dataIndex: "num",
    width: "80px",
    align: "center",
  },
  {
    title: "当月完成数",
    dataIndex: "consumeNum",
    width: "90px",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "60px",
    align: "center",
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "60px",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "addFinishedProduct",
  props: ["visible", "year", "month", "day"],
  data() {
    return {
      tableColumns,
      num: 0,
      consumeNum: 0,
      plan: [],
    };
  },
  mounted() {
    this.form.setFieldsMomentValue({
      time: this.year + "-" + this.month,
    });
    this.$refs.gridPagerChildren.loadData((data) => {});
  },
  methods: {
    setValues(record) {
      this.form1.setFieldsValue({
        id1: record.id,
        name1: record.name,
        unit1: record.unit,
      });
      this.num = record.num;
      this.consumeNum = record.consumeNum;
    },
    hideModalChildren() {
      this.$emit("hideModal");
    },
    addPlanSubmit() {
      let values = this.form1.getFieldsValue();
      let planQueryVo = {
        startDate: this.year + "-" + this.month + "-" + "01",
        endDate: this.year + "-" + (this.month + 1) + "-" + "01",
        id: values.id1,
        num: values.num,
        isCompute: "否",
      };
      if (values.id1 !== undefined) {
        if (values.num + this.consumeNum > this.num) {
          this.$message.error("数量不能超过计划总数");
        } else {
          $api.addFinishedProductInfo(this.form1, planQueryVo, (result) => {
            if (result.data.message == 1) {
              this.$message.error("数量不能超过计划总数");
            } else {
              this.$message.success("录入成功");
              this.$emit("hideModal");
            }
          });
        }
      } else {
        this.$message.error("请选择产成品");
      }
    },
    userPageParams() {
      let planQueryVo = this.form.getFieldsValue();
      if (planQueryVo.time == undefined) {
        planQueryVo.startDate = this.year + "-" + this.month + "-" + "01";
        planQueryVo.endDate = this.year + "-" + (this.month + 1) + "-" + "01";
      } else {
        planQueryVo.startDate =
          planQueryVo.time.format("YYYY") + "-" + planQueryVo.time.format("MM");
        planQueryVo.endDate =
          planQueryVo.time.format("YYYY") +
          "-" +
          (parseInt(planQueryVo.time.format("MM")) + 1);
      }
      return planQueryVo;
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form.resetFields();
      this.$refs.gridPagerChildren.loadData((data) => {});
    },
    queryPlanCondition() {
      let data = this.form.getFieldsValue();
      if (data.id !== undefined || data.time !== undefined) {
        this.$refs.gridPagerChildren.loadData((data) => {});
      }
    },
  },
};
</script>
