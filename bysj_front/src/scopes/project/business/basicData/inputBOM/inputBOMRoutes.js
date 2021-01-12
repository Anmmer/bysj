// 导出该模块路由
export default [
    {
      title: 'BOM资料录入',
      name: 'inputBOM',
      path: 'inputBOM',
      component: () => import('./inputBOM.vue'),
    }
  ]