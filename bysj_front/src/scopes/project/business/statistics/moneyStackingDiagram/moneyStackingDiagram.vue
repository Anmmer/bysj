<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">物料消耗金额条形图</span>
        <span class="top1-text">年份：</span>
        <ta-year-picker
          @change="onChange"
          :defaultValue="moment(2021, 'YYYY')"
          style="position:absolute; left:270px;width:115px"
        />
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
        <div style="height: 520px">
          <ta-echarts
            style="
              position: relative;
              top: 25px;
              left: 120px;
              width: 1000px;
              height: 480px;
              border-width: 0px;
              cursor: default;
            "
            ref="chart"
            :option="option"
          />
        </div>
      </ta-card>
    </div>
  </div>
</template>
<script>
import $api from "../materialStackingDiagram/api/index";
import moment from "moment";
export default {
  name: "moneyStackingDiagram",
  data() {
    return {
      list: [],
      option: {},
      year: 0,
      month: 0,
      day: 0,
    };
  },
  mounted() {
    let date = new Date();
    this.year = date.getFullYear();
    this.getData();
  },
  methods: {
    moment,
    getData() {
      let limit = {};
      limit.startDate = this.year + "-" + "01" + "-" + "01";
      limit.endDate = this.year + 1 + "-" + "01" + "-" + "01";
      $api.getMoneyData(limit, (result) => {
        let option = {
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
          },
          legend: {
            data: ["原材料", "辅助材料"],
          },
          toolbox: {
            show: true,
            feature: {
              dataView: { show: true, readOnly: false },
              restore: { show: true },
              saveAsImage: { show: true },
            },
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: {
            type: "value",
            boundaryGap: [0, 0.01],
          },
          yAxis: {
            type: "category",
            data: [
              "1月",
              "2月",
              "3月",
              "4月",
              "5月",
              "6月",
              "7月",
              "8月",
              "9月",
              "10月",
              "11月",
              "12月",
            ],
          },
          series: result.data.list,
        };
        this.$refs.chart.updateOptions(option);
      });
    },
    onChange(date, dateString) {
      if (date !== null) {
        this.year = parseInt(date.format("yyyy"));
        this.getData();
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
.top1-text {
  font-weight: 450;
  font-size: 15px;
  margin-left: 20px;
}
</style>
