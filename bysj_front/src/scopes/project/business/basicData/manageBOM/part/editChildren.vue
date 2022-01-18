<template>
  <ta-modal
    v-model="visible"
    :destroyOnClose="true"
    :closable="false"
    :footer="null"
    width="1250px"
  >
    <div slot="title">
      <span>选取子件</span>
      <ta-button
        @click="handleCancel"
        style="position: absolute; right: 20px; top: 12px"
        >退出</ta-button
      >
    </div>
    <div style="position: relative; top: 3px; width:1050px">
      <ta-form
        layout="horizontal"
        label-width="80px"
        :formLayout="true"
        :autoFormCreate="
          (form) => {
            this.form = form;
          }
        "
      >
        <ta-form-item label="货品编号" fieldDecoratorId="id" :span="6">
          <ta-input style="width: 150px" maxLength="20" />
        </ta-form-item>
        <ta-form-item label="货品名称" fieldDecoratorId="name" :span="6">
          <ta-input style="width: 150px" maxLength="20" />
        </ta-form-item>
        <ta-form-item :span="5">
          <ta-button @click="queryChildrenCondition" type="primary"
            >查询</ta-button
          >
          <ta-button @click="resetValue" type="primary">重置</ta-button>
        </ta-form-item>
      </ta-form>
    </div>
    <div style="width: 660px">
      <ta-table
        :columns="tableColumns"
        :dataSource="childrenBom"
        :scroll="{ y: 310 }"
        size="middle"
      >
        <a slot="action" slot-scope="text, record" @click="add(record)"
          >添加</a
        ></ta-table
      >
      <ta-pagination
        size="small"
        style="text-align: right; margin-top: 15px;"
        :dataSource.sync="childrenBom"
        :params="userPageParams"
        url="basicInfo/queryWlInfoPage"
        ref="gridPagerChildren"
      />
    </div>
    <div style="width: 500px; position: absolute; left: 700px; top: 80px">
      <p
        style="
          margin-bottom: 30px;
          margin-top: 5px;
          margin-left: 15px;
          font-size: 15px;
        "
      >
        主件信息:
      </p>
      <ta-form
        layout="horizontal"
        :formLayout="true"
        label-width="100px"
        :disabled="true"
        :autoFormCreate="
          (form) => {
            this.form1 = form;
          }
        "
      >
        <ta-form-item label="科目编号" fieldDecoratorId="id1" :span="11">
          <ta-input size="small" />
        </ta-form-item>
        <ta-form-item label="品名" fieldDecoratorId="name1" :span="12">
          <ta-input size="small" />
        </ta-form-item>
        <ta-form-item label="单位" fieldDecoratorId="unit" :span="11">
          <ta-input size="small" />
        </ta-form-item>
        <ta-form-item label="分类名称" fieldDecoratorId="type" :span="12">
          <ta-input size="small" />
        </ta-form-item>
      </ta-form>
    </div>
    <div style="width: 550px; position: absolute; left: 690px; top: 250px">
      <ta-form
        :autoFormCreate="
          (form) => {
            this.form2 = form;
          }
        "
      >
        <ta-table
          :columns="addBomColumns"
          :dataSource="addBom"
          :scroll="{ y: 200 }"
          size="middle"
        >
          <ta-table-edit
            slot="num"
            slot-scope="text, record"
            :editForm="form2"
            :row-editable="true"
            :rules="[{ required: true, message: '不能为空' }]"
            type="inputNumber"
          />
          <ta-table-edit
            :editForm="form2"
            slot="action"
            slot-scope="text, record"
            type="rowEdit"
            rowKey="id"
            :row-editable="true"
            :dataSource="addBom"
            :beforeChange="fnBeforeChange"
            @change="changeData"
            @tableChange="fnTableChange"
            @rowDelete="fnRowDelete"
          />
          <span slot="numTitle">组成量 <ta-icon type="edit"/></span>
        </ta-table>
      </ta-form>
    </div>
    <add-children
      :show="show"
      v-if="show"
      :sid="sid"
      :id="oneMainBom.id"
      @hideModal="hideModal"
      @queryAddBom="queryAddBom"
    />
  </ta-modal>
</template>
<script>
import $api from "../api";
import addChildren from "./addChildren.vue";
const addBomColumns = [
  {
    title: "子件编号",
    dataIndex: "id",
    width: "18%",
  },
  {
    title: "子件品名",
    dataIndex: "name",
    width: "28%",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "12%",
    align: "center",
  },
  {
    dataIndex: "num",
    width: "20%",
    align: "center",
    slots: { title: "numTitle" },
    scopedSlots: { customRender: "num" },
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "30%",
    scopedSlots: { customRender: "action" },
  },
];
const tableColumns = [
  {
    title: "科目编号",
    dataIndex: "id",
    width: "100px",
  },
  {
    title: "品名",
    dataIndex: "name",
    width: "125px",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "60px",
    align: "center",
  },
  {
    title: "分类名称",
    dataIndex: "type",
    width: "90px",
    align: "center",
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "90px",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "editChildren",
  props: ["visible", "oneMainBom"],
  components: { addChildren },
  data() {
    return {
      addBom: [],
      childrenBom: [],
      tableColumns,
      addBomColumns,
      sid: "",
      show: false,
    };
  },
  mounted() {
    this.queryChildren();
    this.queryAddBom();
    this.setValue();
  },
  methods: {
    openModal() {
      this.show = true;
    },
    hideModal() {
      this.show = false;
    },
    setValue() {
      this.form1.setFieldsValue({
        id1: this.oneMainBom.id,
        name1: this.oneMainBom.name,
        unit: this.oneMainBom.unit,
        type: this.oneMainBom.type,
      });
    },
    add(record) {
      const obj = this.addBom.find((obj) => obj.id == record.id);
      if (!obj) {
        if (record.id == this.oneMainBom.id) {
          Modal.info({
            title: "请选择其他数据",
          });
        } else {
          this.sid = record.id;
          this.openModal();
        }
      } else {
        Modal.info({
          title: "子件已添加",
        });
      }
    },
    handleCancel() {
      this.$emit("hideModal");
    },
    userPageParams() {
      let wlQueryVo = this.form.getFieldsValue();
      return wlQueryVo;
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form.resetFields();
      this.$refs.gridPagerChildren.loadData((data) => {});
    },
    queryChildren() {
      this.$refs.gridPagerChildren.loadData((data) => {});
    },
    queryAddBom() {
      $api.queryBomInfoList({ mid: this.oneMainBom.id }, (result) => {
        this.addBom = result.data.bomList;
      });
    },
    queryChildrenCondition() {
      let data = this.form.getFieldsValue();
      if (data.id !== undefined || data.name !== undefined) {
        this.$refs.gridPagerChildren.loadData((data) => {});
      }
    },
    fnBeforeChange({ newData, record, rowKey }, callback) {
      // 单元格数据保存前数据处理（newData：返回改变后的行数据、record：该行的行数据、rowKey：该行的rowKey值、callback：回调函数，可判断数据是否正确）
      // 注意，callback 必须被调用
      callback();
      // 可在此处对表格数据进行处理
      Object.assign(record, newData);
    },
    changeData({ newData, record, rowKey }) {
      //判断是否修改
      if (!$api.isObjectValueEqual(newData, record)) {
        $api.editBomInfo(
          this.form2,
          { mid: this.oneMainBom.id, sid: record.id, num: newData.num },
          (result) => {
            this.$message.success("修改成功");
          }
        );
      }
      // 将行数据修改为新值（newData：返回改变的值、record：该行的行数据、rowKey：该行的rowKey值）
      // 也可当表格编辑状态关闭后，在此处对表格数据进行处理
      Object.assign(record, newData);
    },
    fnTableChange(dataSource) {
      // 将返回有editable属性的表格数据，赋给表格
      this.addBom = dataSource;
    },
    fnRowDelete(deleteId) {
      // 返回主键id，即rowKey的值
      $api.removeBomBySid(
        { sid: deleteId, mid: this.oneMainBom.id },
        (result) => {
          this.$message.success("删除成功");
          this.addBom = this.addBom.filter((item) => item.id !== deleteId);
        }
      );
    },
  },
};
</script>
