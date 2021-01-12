import routesContainer from "@/common/components/routesContainer";

import inputProductionPlan from "../inputProductionPlan/inputProductionPlanRoutes";
import materialRequirementCalculation from "../materialRequirementCalculation/materialRequirementCalculationRoutes";
import purchaseOrder from "../purchaseOrder/purchaseOrderRoutes";
import orderArrivalManagement from "../orderArrivalManagement/orderArrivalManagementRoutes";

const innerRoutes = [
  ...inputProductionPlan,
  ...materialRequirementCalculation,
  ...purchaseOrder,
  ...orderArrivalManagement,
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
