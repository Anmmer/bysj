<template>
    <div class="page-wrapper">
        <ta-config-provider :getPopupContainer="popupContainer" :locale='locale'>
            <template v-if="isRouterAlive=='alive'">
                <keep-alive :include="tabList" :exclude="exList">
                    <router-view :key="key" ></router-view>
                </keep-alive>
            </template>
            <template v-else>
                <keep-alive v-if="isRouterAlive" >
                    <router-view ></router-view>
                </keep-alive>
            </template>
        </ta-config-provider>
    </div>
</template>
<script type="text/jsx">
import zhCN from '@yh/ta404-ui/es/locale-provider/zh_CN'
// import enUS from '@yh/ta404-ui/es/locale-provider/default'
export default {
  name: 'routesContainer',
  props: {
    routesList: Array,
  },
  data () {
    return {
      isRouterAlive: 'alive',
      reloadId: '',
      locale: zhCN,
      tabList: [],
      exList: [],
    }
  },
  created () {
    this.$bus.on('refresh', this.reload)
    this.$bus.on('setTabList', this.setTabList)
  },
  methods: {
    reload (menuId, menuPart) {
      // mounted会执行两次
      // this.reloadId = menuId
      // this.exList.push(menuPart)
      // this.$nextTick(() => {
      //   this.$set(this, 'exList', [])
      //   this.reloadId = ''
      // })
      // 该模块的同级模块都会刷新
      this.isRouterAlive = false
      this.$nextTick(() => {
        this.isRouterAlive = true
      })
    },
    setTabList (list) {
      this.$set(this, 'tabList', list)
    },
    popupContainer (trigger) {
      // 单独处理el-tree的弹出父容器
      if (trigger?.classList?.contains('el-tree-node__label')) {
        return trigger?.parentNode
      }
      // 此处将page-wrapper的第一个子元素作为所有弹出框/窗口的容器
      return this.$el.childNodes[0]
    },
  },
  beforeDestroy () {
    this.$bus.off('refresh', this.reload)
    this.$bus.off('setTabList', this.setTabList)
  },
  computed: {
    // 刷新用
    key () {
      if (this.$route.query._modulePartId_ === this.reloadId) {
        return this.$route.query._modulePartId_ + new Date()
      } else {
        return this.$route.query._modulePartId_
      }
    },
  },
}
</script>
<style scoped type="text/less">
    .page-wrapper {
        height: 100%;
    }
</style>
