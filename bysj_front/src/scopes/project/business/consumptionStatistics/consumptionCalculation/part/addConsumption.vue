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
      <span>选取产成品生产计划</span>
      <ta-button
        @click="hideModalChildren"
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
    <div style="width: 680px">
      <ta-table
        :columns="tableColumns"
        :dataSource="FP"
        :haveSn="true"
        :scroll="{ y: 305 }"
        size="middle"
      >
        <span slot="isCompute" slot-scope="text">
          <ta-tag v-if="text == '是'" type="success">是</ta-tag>
          <ta-tag v-else type="danger">否</ta-tag>
        </span>
      </ta-table>
      <ta-pagination
        size="small"
        style="text-align: right; margin-top: 15px"
        :dataSource.sync="FP"
        :params="userPageParams"
        url="finishedProduct/queryFinishedProductInfoPage"
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
    width: "20%",
    align: "center",
  },
  {
    title: "产品品名",
    dataIndex: "name",
    width: "20%",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "15%",
    align: "center",
  },
  {
    title: "已计算",
    dataIndex: "isCompute",
    width: "15%",
    align: "center",
    scopedSlots: { customRender: "isCompute" },
  },
  {
    title: "数量",
    dataIndex: "num",
    width: "17%",
    align: "center",
  },
];
export default {
  name: "addConsumption",
  props: ["visible", "year", "month", "day"],
  data() {
    return {
      tableColumns,
      num: 0,
      FP: [],
      finishedProductQueryVo: {
        startDate: this.year + "-" + this.month + "-" + "01",
        endDate: this.year + "-" + this.month + "-" + this.day,
      },
    };
  },
  mounted() {
    this.$refs.gridPagerChildren.loadData((data) => {});
  },
  methods: {
    hideModalChildren() {
      this.$emit("hideModal");
    },
    compute() {
      let isCompute = false;
      for (let i = 0; i < this.FP.length; i++) {
        if (this.FP[i].isCompute !== "是") {
          isCompute = true;
        }
      }
      if (isCompute) {
        $api.addConsumptionStatisticsInfo(
          this.finishedProductQueryVo,
          (result) => {
            this.$message.success("计算成功");
            this.$emit("hideModal");
          }
        );
      } else {
        this.$message.error("暂无可计算计划");
      }
    },
    userPageParams() {
      //这里返回的对象是分页要一同提交的参数
      return this.finishedProductQueryVo;
    },
  },
};
</script>
