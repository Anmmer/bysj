import routesContainer from "@/common/components/routesContainer";

// 模块part 声明导入
import basicInfo from "../basicInfo/basicInfoRoutes";
import inputBOM from "../inputBOM/inputBOMRoutes";
import manageBOM from "../manageBOM/manageBOMRoutes"

const innerRoutes = [...basicInfo, ...inputBOM,...manageBOM];
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
