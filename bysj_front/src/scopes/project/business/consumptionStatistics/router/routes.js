import routesContainer from "@/common/components/routesContainer";

import finishedProduct from "../finishedProduct/finishedProductRoutes";
import consumptionCalculation from "../consumptionCalculation/consumptionCalculationRoutes"

const innerRoutes = [...finishedProduct,...consumptionCalculation]

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