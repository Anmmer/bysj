<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">采购订单到货</span>
      </ta-card>
    </div>
    <div
      style="
        padding: 13.5px 13.5px 8px 13.5px;
        width: 100%;
        background-color: #f0f2f5;
      "
    >
      <ta-card>
        <ta-button @click="batchOrderArrival" style="margin-bottom: 5px;"
          >批量到货</ta-button
        >
        <ta-table
          ref="table"
          :rowSelection="rowSelection"
          :show-checkbox="true"
          :columns="tableColumns"
          :dataSource="Order"
          :haveSn="false"
          :scroll="{ y: 393 }"
          size="middle"
        >
          <span slot="isDelivered" slot-scope="text">
            <ta-tag v-if="text == '是'" type="success">是</ta-tag>
            <ta-tag v-else type="danger">否</ta-tag>
          </span>
          <template slot="action" slot-scope="text, record">
            <ta-popconfirm
              v-if="record.isDelivered !== '是'"
              title="确认到货?"
              @confirm="orderArrival(record)"
              cancelText="取消"
              okText="确定"
              okType="primary"
            >
              <ta-icon
                slot="icon"
                type="question-circle-o"
                style="color: red"
              />
              <a>到货</a>
            </ta-popconfirm>
            <span v-if="record.isDelivered == '是'">--</span>
          </template>
        </ta-table>
        <ta-pagination
          style="text-align: right; margin-top: 10px"
          :dataSource.sync="Order"
          :params="userPageParams"
          url="purchaseOrder/queryPurchaseOrderInfoPage"
          ref="gridPager"
        />
      </ta-card>
    </div>
  </div>
</template>
<script>
import $api from "./api/index";

const tableColumns = [
  {
    title: "订单号",
    dataIndex: "orderId",
    width: "21%",
    align: "center",
  },
  {
    title: "物料品号",
    dataIndex: "id",
    width: "10%",
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
    width: "6%",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "6%",
    align: "center",
  },
  {
    title: "已到货",
    dataIndex: "isDelivered",
    width: "7%",
    align: "center",
    scopedSlots: { customRender: "isDelivered" },
  },
  {
    title: "到货日期",
    dataIndex: "deliveredDate",
    width: "10%",
    align: "center",
  },
  {
    title: "供应商",
    dataIndex: "gys",
    width: "18%",
    align: "center",
  },
  {
    title: "操作",
    dataIndex: "action",
    width: "8%",
    align: "center",
    scopedSlots: { customRender: "action" },
  },
];

export default {
  name: "orderArrivalManagement",
  data() {
    const rowSelection = {
      //点击每行的checkbox会触发,点击全选也会触发
      onChange: (selectedRowKeys, selectedRows) => {
        this.selectedRows = selectedRows.filter(
          (item) => item.isDelivered == "否"
        );
      },
    };
    return {
      year: 0,
      month: 0,
      day: 0,
      Order: [],
      selectedRows: [],
      rowSelection,
      tableColumns,
    };
  },
  mounted() {
    this.$refs.gridPager.loadData((data) => {});
  },
  methods: {
    userPageParams() {
      //   let purchaseOrderQueryVo = {};
      let date = new Date();
      let d = new Date(this.year, this.month, 0);
      this.year = date.getFullYear();
      this.month = date.getMonth() + 1;
      this.day = d.getDate();
      //   purchaseOrderQueryVo.startDate = this.year + "-" + this.month + "-" + "01";
      //   purchaseOrderQueryVo.endDate = this.year + "-" + this.month + "-" + this.day;
      //   //这里返回的对象是分页要一同提交的参数
      //   return purchaseOrderQueryVo;
    },
    orderArrival(record) {
      $api.orderArrival(
        { orderId: record.orderId, id: record.id },
        (result) => {
          this.$message.success("到货成功");
          this.$refs.gridPager.loadData((data) => {});
        }
      );
    },
    batchOrderArrival() {
      if (this.selectedRows.length == 0) {
        this.$message.error("请选择");
      } else {
        console.log(JSON.stringify(this.selectedRows));
        $api.batchOrderArrival(
          { list: JSON.stringify(this.selectedRows) },
          (result) => {
            this.$message.success("批量到货成功");
            this.$refs.gridPager.loadData((data) => {});
          }
        );
      }
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
