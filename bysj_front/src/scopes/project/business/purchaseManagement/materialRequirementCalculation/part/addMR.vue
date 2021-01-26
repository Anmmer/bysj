<template>
  <ta-modal
    v-model="visible"
    :destroyOnClose="true"
    :closable="false"
    :footer="null"
    :maskClosable="false"
    width="730px"
  >
    <div slot="title">
      <span>生产计划</span>
      <ta-button
        @click="hideModal"
        style="position: absolute; right: 100px; top: 12px"
        >退出</ta-button
      >
      <ta-button
        type="primary"
        @click="compute"
        style="position: absolute; right: 20px; top: 12px"
        >计算</ta-button
      >
    </div>
    <div style="width: 690px">
      <ta-table
        :columns="tableColumns"
        :dataSource="plan"
        :haveSn="true"
        :scroll="{ y: 305 }"
        size="middle"
      >
        <span slot="isCompute" slot-scope="text">
          <ta-tag v-if="text == '是'" type="success">是</ta-tag>
          <ta-tag v-else type="danger">否</ta-tag>
        </span></ta-table
      >
      <ta-pagination
        size="small"
        style="text-align: right; margin-top: 15px"
        :dataSource.sync="plan"
        :params="userPageParams"
        url="plan/queryPlanInfoPage"
        ref="gridPagerChildren"
      />
    </div>
  </ta-modal>
</template>
<script>
import $api from "../api/index";
const tableColumns = [
  {
    title: "产品品号",
    dataIndex: "id",
    width: "17%",
    align: "center",
  },
  {
    title: "产品品名",
    dataIndex: "name",
    width: "17%",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "15%",
    align: "center",
  },
  {
    title: "数量",
    dataIndex: "num",
    width: "15%",
    align: "center",
  },
  {
    title: "录入时间",
    dataIndex: "inputDate",
    width: "20%",
    align: "center",
  },
  {
    title: "已计算",
    dataIndex: "isCompute",
    width: "15%",
    align: "center",
    scopedSlots: { customRender: "isCompute" },
  },
];
export default {
  name: "addMR",
  props: ["visible", "year", "month", "day"],
  data() {
    return {
      tableColumns,
      plan: [],
    };
  },
  mounted() {
    this.$refs.gridPagerChildren.loadData((data) => {});
  },
  methods: {
    hideModal() {
      this.$emit("hideModal");
    },
    compute() {
      let isCompute = false;
      for(let i = 0;i<this.plan.length;i++){
        if(this.plan[i].isCompute!=="是"){
          isCompute=true;
        }
      }
      if(isCompute){
        $api.addMR((result) => {
          this.$message.success("计算成功");
          this.$emit("hideModal");
        });
      }else{
        this.$message.error("暂无可计算产品")
      }
    },
    userPageParams() {
      let planQueryVo = {};
      planQueryVo.startDate = this.year + "-" + this.month + "-" + "01";
      planQueryVo.endDate = this.year + "-" + this.month + "-" + this.day;
      //这里返回的对象是分页要一同提交的参数
      return planQueryVo;
    },
  },
};
</script>