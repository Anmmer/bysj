import { Vue, store, taMixins } from "common/js/public-ant.js"; // 引入公共文件
import router from "./router/index"; // 引入 改模块的路由模块

import {echarts} from '@yh/ta404-ui';

import echartsGL from "echarts-gl";


Vue.use(echarts,echartsGL);

// 把组件化页面挂载到节点app上
new Vue({
    mixins: [taMixins],
    router,
    store,
  }).$mount("#app");