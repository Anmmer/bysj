<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">测试</span>
        <span class="top1-text">(123456)</span>
      </ta-card>
    </div>
    <div style="height:565px">
      <ta-border-layout
        :layout="{
          header: '70px',
          right: '250px',
        }"
      >
        <div slot="header">
          我是header
        </div>
        <div>
          我是主要内容页
          <a href="/basicData.html#/basicInfo"> 跳转</a>
        </div>
        <div slot="centerExtraContent">
          <ta-button>新增</ta-button>
        </div>
        <div slot="right">
          right 右侧内容
        </div>
        <div slot="rightExtraContent">
          右侧工具条的插槽
        </div>
      </ta-border-layout>
    </div>
  </div>
</template>

<script>
import partComponent from "./part/partComponent";

const name = "张三";
export default {
  name: "modulePart1",
  components: { partComponent },
  data() {
    return {
      name,
    };
  },
};
</script>

<style scoped type="text/less">
.top-text {
  font-weight: 550;
  font-size: 20px;
  margin-left: 10px;
}
.top1-text {
  font-weight: 550;
  font-size: 15px;
}
</style>
