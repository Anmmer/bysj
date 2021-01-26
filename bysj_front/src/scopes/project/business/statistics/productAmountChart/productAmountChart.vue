<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">产成品费用堆积图</span>
        <span class="top1-text">年份：</span>
        <ta-year-picker
          @change="onChange"
          :defaultValue="moment(year, 'YYYY')"
          style="position:absolute; left:245px;width:115px"
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
  name: "productAmountChart",
  data() {
    return {
      tree: {},
      option: {},
      year: 0,
      month: 0,
      day: 0,
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
      $api.getProductAmountData(limit, (result) => {
        result.data.list[0].areaStyle = {};
        result.data.list[1].areaStyle = {};
        result.data.list[2].areaStyle = {};
        let option = {
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "cross",
              label: {
                backgroundColor: "#6a7985",
              },
            },
          },
          legend: {
            data: ["半成品加工费用", "原材料费用", "辅助材料费用"],
          },
          toolbox: {
            feature: {
              magicType: { show: true, type: ["bar"] },
              dataView: { show: true, readOnly: true },
              restore: { show: true },
              saveAsImage: {},
            },
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: [
            {
              type: "category",
              boundaryGap: false,
              data: result.data.nameList,
            },
          ],
          yAxis: [
            {
              type: "value",
            },
          ],
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
  font-weight: 540;
  font-size: 15px;
  margin-left: 20px;
}
</style>
