// 导出该模块路由
export default [
  {
    title: '物料基础信息管理',
    name: 'basicInfo',
    path: 'basicInfo',
    component: () => import('./basicInfo.vue'),
  }
]
