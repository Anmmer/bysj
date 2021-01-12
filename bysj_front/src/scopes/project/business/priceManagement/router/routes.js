import routesContainer from "@/common/components/routesContainer";

import enterRawPrice from "../enterRawPrice/enterRawPriceRoutes"
import enterAuxiliaryPrice from "../enterAuxiliaryPrice/enterAuxiliaryPriceRoutes"
import enterPartiallyPrice from "../enterPartiallyPrice/enterPartiallyPriceRoutes"

const innerRoutes = [...enterRawPrice,...enterAuxiliaryPrice,...enterPartiallyPrice]

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
