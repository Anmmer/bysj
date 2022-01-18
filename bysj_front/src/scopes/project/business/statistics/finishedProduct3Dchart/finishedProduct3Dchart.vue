<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">产成品生产3D图</span>
        <span class="top1-text">年份：</span>
        <ta-year-picker
          @change="onChange"
          :defaultValue="moment(year, 'YYYY')"
          style="position:absolute; left:235px;width:115px"
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
              top: 0px;
              left: 80px;
              width: 1000px;
              height: 530px;
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
  name: "finishedProduct3Dchart",
  data() {
    return {
      list: [],
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
      $api.getProduct3DChart(limit, (result) => {
        let option = {
          tooltip: {},
          toolbox: {
            show: true,
            feature: {
              dataView: {
                show: true,
                readOnly: true,
                optionToContent: function (opt) {
                  let xAxisData = opt.xAxis3D[0].data; //坐标数据
                  let yAxisData = opt.yAxis3D[0].data; //坐标数据
                  let series = opt.series; //坐标数据
                  let tdHeads = '<td  style="padding: 0 10px">月份</td><td  style="padding: 0 10px">产成品</td><td  style="padding: 0 10px">数量</td>'; //表头
                  let tdBodys = ""; //数据
                  let table = `<table border="1" style="margin-left:20px;border-collapse:collapse;font-size:14px;text-align:center"><tbody><tr>${tdHeads} </tr>`;
                  for (let i = 0, l = yAxisData.length; i < l; i++) {
                    for (let j = 0; j < xAxisData.length; j++) {
                      //组装表数据
                    table += `<tr><td style="padding: 0 10px">${yAxisData[i]}</td><td>${xAxisData[j]}</td><td>${(series[0].data[l*j+i]).value[2]}</td></tr>`;
                    }
                    tdBodys = "";
                  }
                  table += "</tbody></table>";
                  return table;
                },
              },
              saveAsImage: { show: true },
            },
          },
          visualMap: {
            max: result.data.max,
            inRange: {
              color: [
                "#313695",
                "#4575b4",
                "#74add1",
                "#abd9e9",
                "#e0f3f8",
                "#ffffbf",
                "#fee090",
                "#fdae61",
                "#f46d43",
                "#d73027",
                "#a50026",
              ],
            },
          },
          xAxis3D: {
            type: "category",
            data: result.data.nameList,
          },
          yAxis3D: {
            type: "category",
            data: [
              "一月",
              "二月",
              "三月",
              "四月",
              "五月",
              "六月",
              "七月",
              "八月",
              "九月",
              "十月",
              "十一月",
              "十二月",
            ],
          },
          zAxis3D: {
            type: "value",
          },
          grid3D: {
            boxWidth: 200,
            boxDepth: 100,
            viewControl: {
              // projection: 'orthographic'
            },
            light: {
              main: {
                intensity: 1.2,
                shadow: true,
              },
              ambient: {
                intensity: 0.3,
              },
            },
          },
          series: [
            {
              type: "bar3D",
              data: result.data.list.map(function (item) {
                return {
                  value: [item[0], item[1], item[2]],
                };
              }),
              shading: "lambert",

              label: {
                textStyle: {
                  fontSize: 16,
                  borderWidth: 1,
                },
              },

              emphasis: {
                label: {
                  textStyle: {
                    fontSize: 20,
                    color: "#900",
                  },
                },
                itemStyle: {
                  color: "#900",
                },
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
  font-weight: 450;
  font-size: 15px;
  margin-left: 20px;
}
</style>
