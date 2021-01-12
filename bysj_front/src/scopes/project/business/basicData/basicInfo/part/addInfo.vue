<template>
  <ta-drawer
    placement="right"
    :title="type == '1' ? '修改物料信息' : '添加物料信息'"
    :closable="true"
    @close="onClose"
    :visible="visible"
    width="500"
  >
    <ta-form
      :autoFormCreate="
        (form) => {
          this.form = form;
        }
      "
    >
      <ta-form-item
        label="货品名称"
        fieldDecoratorId="name"
        :require="{ message: '请输入!' }"
      >
        <ta-input style="width: 300px" />
      </ta-form-item>
      <ta-form-item
        style="margin-top: 20px"
        label="规格"
        fieldDecoratorId="standard"
        :require="{ message: '请输入!' }"
      >
        <ta-input style="width: 300px" />
      </ta-form-item>
      <ta-form-item
        style="margin-top: 20px"
        label="货品类别"
        fieldDecoratorId="type"
        :require="{ message: '请输入!' }"
      >
        <ta-input style="width: 300px" />
      </ta-form-item>
      <ta-form-item
        style="margin-top: 20px"
        label="单位"
        fieldDecoratorId="unit"
        :require="{ message: '请输入!' }"
      >
        <ta-input style="width: 300px" />
      </ta-form-item>
      <ta-form-item
        style="margin-top: 20px"
        label="供应商"
        fieldDecoratorId="gysid"
        :require="{ message: '请选择!' }"
      >
        <ta-select style="width: 300px">
          <ta-select-option v-for="s in this.gysList" :key="s.gysid">{{
            s.gys
          }}</ta-select-option></ta-select
        >
      </ta-form-item>
    </ta-form>
    <div slot="footer">
      <ta-button @click="resetValue">重置</ta-button>
      <ta-button type="primary" @click="handleSubmit">保存</ta-button>
    </div>
  </ta-drawer>
</template>
<script>
import $api from "../api";
export default {
  name: "addInfo",
  props: ["gysList", "id", "type", "visible"],
  data() {
    return {};
  },
  mounted() {
    if (this.type == 1) {
      this.setValue();
    }
  },
  methods: {
    handleSubmit() {
      let values = this.form.getFieldsValue();
      // this.form.validateFields((err, values) => {
      //   if (!err) {
      if (this.type == 1) {
        values.id = this.id;
        $api.editWL(this.form, values, (result) => {
          this.$emit("addSuccess");
          this.$message.success("修改成功");
          this.resetValue();
        });
      } else {
        $api.addWL(this.form, values, (result) => {
          this.$emit("addSuccess");
          this.$message.success("添加成功");
          this.resetValue();
        });
      }
      //   }
      // });
    },
    onClose() {
      this.$emit("onClose");
    },
    setValue() {
      $api.queryWLById(this.form, { id: this.id }, (result) => {
        this.form.setFieldsValue({
          name: result.data.wlInfo.name,
          unit: result.data.wlInfo.unit,
          type: result.data.wlInfo.type,
          standard: result.data.wlInfo.standard,
          gysid: result.data.wlInfo.gysid,
        });
      });
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form.resetFields();
    },
  },
};
</script>
