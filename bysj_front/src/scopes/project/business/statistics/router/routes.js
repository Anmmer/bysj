import routesContainer from "@/common/components/routesContainer";

import materialStackingDiagram from "../materialStackingDiagram/materialStackingDiagramRoutes";
import materielPieChart from "../materielPieChart/materielPieChartRoutes";
import moneyStackingDiagram from "../moneyStackingDiagram/moneyStackingDiagramRoutes";
import finishedProduct3Dchart from "../finishedProduct3Dchart/finishedProduct3DchartRoutes";
import productAmountChart from "../productAmountChart/productAmountChartRoutes";
import productSelfChart from "../productSelfChart/productSelfChartRoutes";
import funnelSupplierChart from "../funnelSupplierChart/funnelSupplierChartRoutes";

const innerRoutes = [
  ...materialStackingDiagram,
  ...materielPieChart,
  ...moneyStackingDiagram,
  ...finishedProduct3Dchart,
  ...productAmountChart,
  ...productSelfChart,
  ...funnelSupplierChart,
];
// 导入路由声明
export default [
  {
    path: "/",
    component: routesContainer,
    children: innerRoutes.map((item) => ({
      ...item,
    })),
  },
];
