<template>
  <div class="iframeList" ref="iframeCon">
    <ta-page-tool v-if="isPageTool" :toolMenu="toolMenu" :isRefresh="false"></ta-page-tool>
    <iframe
            :id="worktable.id"
            :style="worktableShow?'':'display:none'"
            :src="worktable.src"
            frameborder="none"
            frameBorder="0"
            scrolling="auto">
    </iframe>
  </div>
</template>
<script>
import { mapGetters, } from 'vuex'

export default {
  name: 'iframe-list',
  data () {
    return {
      src: '',
      CacheWebStorage: null,
      toolMenu: [{
        icon: 'rocket',
        name: '刷新全部',
        onClick: () => {
          this.CacheWebStorage.cleanData()
          window.location.reload()
        },
      }, {
        icon: 'sync',
        name: '刷新本页',
        onClick: () => {
          top.indexTool.reload()
        },
      }],
      iframeList: {},
      worktableShow: true,
    }
  },
  created () {
    this.CacheWebStorage = this.Base.webStorage.init('Ta$CacheStorage')
  },
  computed: {
    ...mapGetters({
      tabList: 'getTabMenuList',
      activeTab: 'getActiveIframe',
      state: 'getStateValue',
    }),
    isPageTool () {
      const { notPageTool, } = this.state
      /* faceConfig.js 中配置的菜单不显示 */
      return notPageTool.indexOf(this.activeTab.module) == -1 && notPageTool.indexOf(this.activeTab.partId) == -1
    },
    worktable () {
      const { worktable, } = this.state
      const cg = {
        ...worktable,
        partId: 'worktable',
      }
      this.iframeList[this.createId(cg)] = cg
      return {
        id: this.createId(cg),
        src: this.createSrc(cg),
      }
    },
  },
  methods: {
    createIframe (menu) {
      const iframe = document.createElement('iframe')
      iframe.setAttribute('frameBorder', 'none')
      iframe.setAttribute('frameBorder', '0')
      iframe.setAttribute('scrolling', 'auto')
      iframe.id = this.createId(menu)
      iframe.style.width = '100%'
      iframe.style.height = '100%'
      const src = this.createSrc(menu)
      iframe.setAttribute('src', src)
      return iframe
    },
    createSrc (menu) {
      let src = ''; let _modulePartId_ = ''
      src = this.createId(menu) + '#/' + menu.part
      _modulePartId_ = '_modulePartId_=' + menu.partId
      if (src.indexOf('?') > -1) {
        src += '&' + _modulePartId_
      } else {
        src += '?' + _modulePartId_
      }
      return src
    },
    createId (menu) {
      return menu.prefix + menu.module
    },
    hideIframe (menu) {
      if (menu.id == 'worktable') {
        this.worktableShow = false
        return
      }
      const el = document.getElementById(this.createId(menu))
      if (el) {
        el.style.display = 'none'
      }
    },
    showIframe (menu) {
      if (menu.id == 'worktable') {
        this.worktableShow = false
        return
      }
      const el = document.getElementById(this.createId(menu))
      const src = this.createSrc(menu)
      el.setAttribute('src', src)
      if (el) {
        el.style.display = 'block'
      }
    },
    menuToModule (menu) {
      if (menu.url) {
        return {
          partId: menu.id,
          module: menu.url.split('#/')[0],
          part: menu.url.split('#/')[1] || '',
          prefix: menu.prefix || this.state.srcPrefix,
        }
      } else {
        return {
          module: '404.html',
          part: '',
          partId: 'ta404',
          prefix: menu.prefix || this.state.srcPrefix,
        }
      }
    },
    deleteIframe (list) {
      list.map((item) => {
        if (this.iframeList[item] && this.iframeList[item].partId !== 'worktable') {
          delete this.iframeList[item]
          const el = document.getElementById(item)
          if (el) {
            el.parentNode.removeChild(el)
          }
        }
      })
    },
  },
  watch: {
    activeTab (nowTab, oldTab) {
      const mdId = this.createId(nowTab)
      if (this.iframeList[mdId]) {
        this.showIframe(nowTab)
        this.iframeList[mdId] = { ...nowTab, }
      } else {
        this.iframeList[mdId] = { ...nowTab, }
        this.createIframe(nowTab)
        this.$refs.iframeCon.appendChild(this.createIframe(nowTab))
      }
      if (mdId == this.createId(oldTab)) {
      } else {
        this.hideIframe(oldTab)
      }
    },
    tabList (list) {
      // 不在tablist中的iframe 应该删除
      const iList = Object.keys(this.iframeList)
      const listModule = []
      const tabL1 = list.map((item) => {
        listModule.push(this.menuToModule(item).part)
        return this.createId(this.menuToModule(item))
      })
      const tabL2 = [...new Set(tabL1)]
      const arr = iList.filter(item => tabL2.indexOf(item) < 0)
      this.deleteIframe(arr)
      // 把tabList的变更发送到每个iframe页面,子页面可刷新
      for (let i = 0; i < tabL2.length; i++) {
        const pageObj = document.getElementById(tabL2[i]).contentWindow.pageVmObj
        pageObj && pageObj.$bus.emit('setTabList', listModule)
      }
    },
  },
}
</script>
<style scoped lang="less" type="text/less">
  .iframeList {
    width: 100%;
    height: 100%;
    .user-select();
  }

  iframe {
    width: 100%;
    height: 100%;
  }
</style>
