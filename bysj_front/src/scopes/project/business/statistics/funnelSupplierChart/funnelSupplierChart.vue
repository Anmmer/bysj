<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">供应商供货占比漏斗图</span>
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
              top: 0px;
              left: 80px;
              width: 1000px;
              height: 540px;
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
export default {
  name: "funnelSupplierChart",
  data() {
    return {
      list: [],
      option: {},
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      $api.getFunnelSupplierData((result) => {
        let option = {
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c}%",
          },
          toolbox: {
            feature: {
              dataView: { show: true, readOnly: true },
              saveAsImage: {},
            },
          },
          // legend: {
          //   data: result.data.names,
          // },

          series: [
            {
              name: "漏斗图",
              type: "funnel",
              left: "10%",
              top: 60,
              //x2: 80,
              bottom: 60,
              width: "80%",
              // height: {totalHeight} - y - y2,
              min: 0,
              max: 60,
              minSize: "0%",
              maxSize: "100%",
              sort: "descending",
              gap: 2,
              label: {
                show: true,
                position: "inside",
              },
              labelLine: {
                length: 10,
                lineStyle: {
                  width: 1,
                  type: "solid",
                },
              },
              itemStyle: {
                borderColor: "#fff",
                borderWidth: 1,
              },
              emphasis: {
                label: {
                  fontSize: 15,
                },
              },
              data: result.data.list,
            },
          ],
        };
        this.$refs.chart.updateOptions(option);
      });
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
