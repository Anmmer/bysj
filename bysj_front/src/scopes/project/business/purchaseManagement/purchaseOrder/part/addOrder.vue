<template>
  <ta-modal
    v-model="visible"
    :destroyOnClose="true"
    :closable="false"
    :footer="null"
    :maskClosable="false"
    width="910px"
  >
    <div slot="title">
      <span>需求计划</span>
      <ta-button
        @click="hideModal"
        style="position: absolute; right: 80px; top: 12px"
        >退出</ta-button
      >
      <ta-button
        type="primary"
        @click="compute"
        style="position: absolute; right: 20px; top: 12px"
        >生成</ta-button
      >
    </div>
    <div style="width: 860px">
      <ta-table
        :columns="tableColumns"
        :dataSource="MR"
        :haveSn="false"
        :scroll="{ y: 305 }"
        size="middle"
      >
        <span slot="isOrder" slot-scope="text">
          <ta-tag v-if="text == '是'" type="success">是</ta-tag>
          <ta-tag v-else type="danger">否</ta-tag>
        </span></ta-table
      >
      <ta-pagination
        size="small"
        style="text-align: right; margin-top: 15px"
        :dataSource.sync="MR"
        :params="userPageParams"
        url="plan/queryMRPage"
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
    width: "12%",
    align: "center",
  },
  {
    title: "产品品名",
    dataIndex: "name",
    width: "15%",
    align: "center",
  },
  {
    title: "数量",
    dataIndex: "pnum",
    width: "8%",
    align: "center",
  },
  {
    title: "物料编号",
    dataIndex: "sid",
    width: "12%",
    align: "center",
  },
  {
    title: "物料名称",
    dataIndex: "sname",
    width: "20%",
    align: "center",
  },
  {
    title: "数量",
    dataIndex: "num",
    width: "8%",
    align: "center",
  },
  {
    title: "已生成采购订单",
    dataIndex: "isOrder",
    width: "15%",
    align: "center",
    scopedSlots: { customRender: "isOrder" }
  },
];
export default {
  name: "addOrder",
  props: ["visible", "year", "month", "day"],
  data() {
    return {
      tableColumns,
      MR: [],
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
      for(let i = 0;i<this.MR.length;i++){
        if(this.MR[i].isOrder!=="是"){
          isCompute=true;
        }
      }
      if(isCompute){
        $api.addOrder((result) => {
          this.$message.success("生成成功");
          this.$emit("hideModal");
        });
      }else{
        this.$message.error("暂无需求计划")
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