<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">自制半成品产量、生产费用统计图</span>
        <span class="top1-text">年份：</span>
        <ta-year-picker
          @change="onChange"
          :defaultValue="moment(year, 'YYYY')"
          style="position:absolute; left:390px;width:115px"
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
              top: 35px;
              left: 80px;
              width: 1100px;
              height: 500px;
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
  name: "productSelfChart",
  data() {
    return {
      tree: {},
      option: {},
      year: 0,
    };
  },
  created() {
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
      $api.getProductSelfChartData(limit, (result) => {
        let option = {
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "cross",
              crossStyle: {
                color: "#999",
              },
            },
          },
          legend: {
            data: ["产量", "生产费用"],
          },
          toolbox: {
            show: true,
            feature: {
              magicType: { show: true, type: ["line", "bar"] },
              dataView: { show: true, readOnly: true },
              restore: { show: true },
              saveAsImage: { show: true },
            },
          },
          calculable: true,
          xAxis: [
            {
              type: "category",
              axisPointer: {
                type: "shadow",
              },
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
          ],
          yAxis: [
            {
              type: "value",
              name: "产量",
              min: 0,
              max: result.data.char1Max,
              axisLabel: {
                formatter: "{value} 个",
              },
            },
            {
              type: "value",
              name: "生产费用",
              min: 0,
              max: result.data.Char2Max,
              axisLabel: {
                formatter: "{value} 元",
              },
            },
          ],
          series: [
            {
              name: "产量",
              type: "bar",
              data: result.data.barChar1.data,
              markPoint: {
                data: [
                  { type: "max", name: "最大值" },
                  { type: "min", name: "最小值" },
                ],
              },
              markLine: {
                data: [{ type: "average", name: "平均值" }],
              },
            },
            {
              name: "生产费用",
              type: "bar",
              yAxisIndex: 1,
              data: result.data.barChar2.data,
              markPoint: {
                data: [
                  { type: "max", name: "最大值" },
                  { type: "min", name: "最小值" },
                ],
              },
              markLine: {
                data: [{ type: "average", name: "平均值" }],
              },
            },
          ],
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
  font-weight: 540;
  font-size: 15px;
  margin-left: 20px;
}
</style>
