<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">物料需求计算：{{ year }}-{{ month }}</span>
      </ta-card>
    </div>
    <div style="padding: 13.5px 13.5px 8px 13.5px; width: 100%; background-color: #f0f2f5">
      <ta-card>
        <div style="height: 37px; margin-top: 3px; margin-bottom: 5px">
          <ta-button @click="openModal">录入</ta-button>
        </div>
        <ta-table
          :columns="tableColumns"
          :dataSource="MR"
          :haveSn="true"
          :scroll="{ y: 379 }"
        >
        </ta-table>
        <ta-pagination
          style="text-align: right; margin-top: 10px"
          :dataSource.sync="MR"
          :params="userPageParams"
          url="plan/queryMRPage"
          ref="gridPager"
        />
        <add-MR
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
</template>
<script>
import addMR from "./part/addMR.vue";
const tableColumns = [
  {
    title: "产品品号",
    dataIndex: "id",
    width: "8%",
    align: "center",
  },
  {
    title: "产品品名",
    dataIndex: "name",
    width: "12%",
    align: "center",
  },
  {
    title: "数量",
    dataIndex: "pnum",
    width: "8%",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "10%",
    align: "center",
  },
  {
    title: "计算时间",
    dataIndex: "computeDate",
    width: "12%",
    align: "center",
  },
  {
    title: "物料编号",
    dataIndex: "sid",
    width: "15%",
    align: "center",
  },
  {
    title: "物料名称",
    dataIndex: "sname",
    width: "15%",
    align: "center",
  },
  {
    title: "数量",
    dataIndex: "num",
    width: "10%",
    align: "center",
  },
  {
    title: "物料单位",
    dataIndex: "sunit",
    width: "10%",
    align: "center",
  },
];
export default {
  name: "materialRequirementCalculation",
  components: { addMR },
  data() {
    return {
      year: 0,
      month: 0,
      day: 0,
      visible: false,
      MR: [],
      tableColumns,
    };
  },
  mounted() {
    this.$refs.gridPager.loadData((data) => {});
  },
  methods: {
    userPageParams() {
      let mrQueryVo = {};
      let date = new Date();
      let d = new Date(this.year, this.month, 0);
      this.year = date.getFullYear();
      this.month = date.getMonth() + 1;
      this.day = d.getDate();
      mrQueryVo.startDate = this.year + "-" + this.month + "-" + "01";
      mrQueryVo.endDate = this.year + "-" + this.month + "-" + this.day;
      //这里返回的对象是分页要一同提交的参数
      return mrQueryVo;
    },
    openModal() {
      this.visible = true;
    },
    hideModal() {
      this.visible = false;
      this.$refs.gridPager.loadData((data) => {});
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
