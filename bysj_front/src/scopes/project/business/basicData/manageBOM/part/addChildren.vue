<template>
  <ta-modal
    title="输入数量"
    v-model="show"
    :destroyOnClose="true"
    :closable="false"
    width="320px"
  >
    <template slot="footer">
      <ta-button key="back" @click="hideModal">退出</ta-button>
      <ta-button key="submit" type="primary" @click="addSubmit">
        保存
      </ta-button>
    </template>
    <ta-form
      label-width="80px"
      :autoFormCreate="
        (form) => {
          this.form = form;
        }
      "
    >
      <ta-form-item label="编号" :disabled="true" fieldDecoratorId="sid">
        <ta-input style="width:150px" />
      </ta-form-item>
      <ta-form-item
        label="数量"
        fieldDecoratorId="num"
        :require="{ message: '请输入!' }"
      >
        <ta-input-number
          :min="0"
          :max="1000"
          style="width:150px"
        /> </ta-form-item
    ></ta-form>
  </ta-modal>
</template>
<script>
import $api from "../api";
export default {
  name: "addChildren",
  props: ["show", "sid", "id"],
  data() {
    return {};
  },
  mounted() {
    this.setValue();
  },
  methods: {
    setValue() {
      this.form.setFieldsValue({
        sid: this.sid,
      });
    },
    hideModal() {
      this.$emit("hideModal");
    },
    addSubmit() {
      let values = this.form.getFieldsValue();
      values.mid = this.id;
      $api.addBomInfo(this.form, values, (result) => {
        this.$message.success("添加成功");
        this.hideModal();
        this.$emit("queryAddBom");
      });
    },
  },
};
</script>
