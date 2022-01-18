<template>
  <ta-input id="input" />
</template>

<script>
let input = document.querySelector("#input");
input.oninput = function () {
  console.log(this.value);
};
export default {
  name: "partComponent",
  props: {
    buttonKey: {
      type: String,
    },
  },
  data() {
    return {};
  },
};
</script>

<style scoped type="text/less">
</style>
