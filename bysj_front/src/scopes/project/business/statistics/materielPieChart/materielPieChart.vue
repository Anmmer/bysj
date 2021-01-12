<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">物料数量饼图</span>
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
              top: 15px;
              left: 60px;
              width: 900px;
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
import $api from "../stackingDiagram/api/index";
export default {
  name: "materielPieChart",
  data() {
    return {
      tree: {},
      option: {},
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      $api.materielPieChart((result) => {
        let option = {
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b}: {c} ({d}%)",
          },
          legend: {
            orient: "vertical",
            left: 0,
            data: ["原材料", "自制半成品", "辅助材料", "产成品", "低值易耗品"],
          },
          // series: [
          //   {
          //     name: "访问来源",
          //     type: "pie",
          //     radius: "55%",
          //     center: ["50%", "60%"],
          //     data: result.data.list,
          //     emphasis: {
          //       itemStyle: {
          //         shadowBlur: 10,
          //         shadowOffsetX: 0,
          //         shadowColor: "rgba(0, 0, 0, 0.5)",
          //       },
          //     },
          //   },
          // ],
          series: [
            {
              name: "物料数量",
              type: "pie",
              radius: ["50%", "70%"],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: "center",
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: "30",
                  fontWeight: "bold",
                },
              },
              labelLine: {
                show: false,
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
