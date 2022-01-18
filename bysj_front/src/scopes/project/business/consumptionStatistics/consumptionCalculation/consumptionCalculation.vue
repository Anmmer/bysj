<template>
  <div>
    <ta-card>
      <span slot="title">原料消耗统计：{{ year }}-{{ month }}</span>
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
          <ta-input placeholder="请输入" style="width: 180px" />
        </ta-form-item>
        <ta-form-item :span="6">
          <ta-button @click="queryFPCondition" type="primary">
            <ta-icon type="search" />查询
          </ta-button>
          <ta-button @click="resetValue" type="primary">
            <ta-icon type="reload" />重置
          </ta-button>
        </ta-form-item>
      </ta-form>
    </ta-card>
    <ta-card>
      <ta-button @click="openModal" style="margin-bottom: 5px;" type="primary">
        <ta-icon type="plus" />录入
      </ta-button>
      <ta-table
        :columns="tableColumns"
        :dataSource="consumptionStatistics"
        :scroll="{ y: 350 }"
        size="middle"
      ></ta-table>
      <ta-pagination
        style="text-align: right; margin-top: 10px"
        :dataSource.sync="consumptionStatistics"
        :params="userPageParams"
        url="consumptionStatistics/queryConsumptionStatisticsInfoPage"
        ref="gridPager"
      />
      <add-consumption
        :visible="visible"
        :year="year"
        :month="month"
        :day="day"
        v-if="visible"
        @hideModal="hideModal"
      />
    </ta-card>
  </div>
</template>
<script>
import $api from "./api/index";
import addConsumption from "./part/addConsumption.vue";
const tableColumns = [
  {
    title: "原料品号",
    dataIndex: "id",
    width: "15%",
  },
  {
    title: "原料品名",
    dataIndex: "name",
    width: "15%",
  },
  {
    title: "产品类别",
    dataIndex: "type",
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
    title: "单位",
    dataIndex: "unit",
    width: "15%",
    align: "center",
  },
  {
    title: "录入时间",
    dataIndex: "inputDate",
    width: "18%",
    align: "center",
  },
];
export default {
  name: "consumptionCalculation",
  components: { addConsumption },
  data() {
    return {
      visible: false,
      year: 0,
      month: 0,
      day: 0,
      num: 0,
      consumptionStatistics: [],
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
      let ConsumptionStatisticsQueryVo = this.form.getFieldsValue();
      let date = new Date();
      let d = new Date(this.year, this.month, 0);
      this.year = date.getFullYear();
      this.month = date.getMonth() + 1;
      this.day = d.getDate();
      ConsumptionStatisticsQueryVo.startDate =
        this.year + "-" + this.month + "-" + "01";
      ConsumptionStatisticsQueryVo.endDate =
        this.year + "-" + this.month + "-" + this.day;
      //这里返回的对象是分页要一同提交的参数
      return ConsumptionStatisticsQueryVo;
    },
    queryFPCondition() {
      let data = this.form.getFieldsValue();
      if (data.id !== undefined || data.name !== undefined) {
        this.$refs.gridPager.loadData((data) => {});
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
