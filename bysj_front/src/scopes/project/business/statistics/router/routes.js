import routesContainer from "@/common/components/routesContainer";

import stackingDiagram from "../stackingDiagram/stackingDiagramRoutes";
import materielPieChart from "../materielPieChart/materielPieChartRoutes"

const innerRoutes = [...stackingDiagram,...materielPieChart];
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
