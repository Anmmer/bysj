<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">采购订单：{{ year }}-{{ month }}</span>
      </ta-card>
    </div>
    <div
      style="padding: 13.5px 13.5px 8px 13.5px; width: 100%; background-color: #f0f2f5"
    >
      <ta-card>
        <ta-button @click="openModal" style="margin-bottom: 5px;"
          >生成</ta-button
        >
        <ta-table
          :columns="tableColumns"
          :dataSource="Order"
          :haveSn="true"
          :scroll="{ y: 393 }"
          size="middle"
        >
        </ta-table>
        <ta-pagination
          style="text-align: right; margin-top: 10px"
          :dataSource.sync="Order"
          :params="userPageParams"
          url="purchaseOrder/queryPurchaseOrderInfoPage"
          ref="gridPager"
        />
        <add-order
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
import addOrder from "./part/addOrder.vue";
const tableColumns = [
  {
    title: "订单号",
    dataIndex: "orderId",
    width: "20%",
    align: "center",
  },
  {
    title: "生成日期",
    dataIndex: "inputDate",
    width: "12%",
    align: "center",
  },
  {
    title: "物料品号",
    dataIndex: "id",
    width: "12%",
    align: "center",
  },
  {
    title: "物料品名",
    dataIndex: "name",
    width: "15%",
    align: "center",
  },
  {
    title: "数量",
    dataIndex: "num",
    width: "8%",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "8%",
    align: "center",
  },
  {
    title: "供应商",
    dataIndex: "gys",
    width: "20%",
    align: "center",
  },
];
export default {
  name: "purchaseOrder",
  components: { addOrder },
  data() {
    return {
      year: 0,
      month: 0,
      day: 0,
      visible: false,
      Order: [],
      tableColumns,
    };
  },
  mounted() {
    this.$refs.gridPager.loadData((data) => {});
  },
  methods: {
    userPageParams() {
      let purchaseOrderQueryVo = {};
      let date = new Date();
      let d = new Date(this.year, this.month, 0);
      this.year = date.getFullYear();
      this.month = date.getMonth() + 1;
      this.day = d.getDate();
      purchaseOrderQueryVo.startDate =
        this.year + "-" + this.month + "-" + "01";
      purchaseOrderQueryVo.endDate =
        this.year + "-" + this.month + "-" + this.day;
      //这里返回的对象是分页要一同提交的参数
      return purchaseOrderQueryVo;
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
