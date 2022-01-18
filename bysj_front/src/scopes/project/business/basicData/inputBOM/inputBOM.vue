<template>
  <div>
    <ta-card>
      <template slot="title">
        <span >产品结构</span>
        <span class="top1-text">(BOM)</span>
        <span >资料录入</span>
      </template>
      <ta-form
        style="margin-top:10px"
        layout="horizontal"
        :formLayout="true"
        :autoFormCreate="
              (form) => {
                this.form1 = form;
              }
            "
      >
        <ta-form-item label="货品编号" fieldDecoratorId="id" :span="5">
          <ta-input style="width: 180px" maxlength="20" placeholder="请输入" />
        </ta-form-item>
        <ta-form-item label="货品名称" fieldDecoratorId="name" :span="5">
          <ta-input style="width: 180px" maxlength="20" placeholder="请输入" />
        </ta-form-item>
        <ta-form-item label="分类名称" fieldDecoratorId="type" :span="5">
          <ta-select collectionType="PRODUCTIONTYPE" style="width: 180px" maxlength="20" placeholder="全部" />
        </ta-form-item>
        <ta-form-item :span="6">
          <ta-button @click="queryWLCondition" type="primary">
            <ta-icon type="search" />查&nbsp;询
          </ta-button>
          <ta-button @click="resetValue" type="primary">
            <ta-icon type="reload" />重&nbsp;置
          </ta-button>
        </ta-form-item>
      </ta-form>
    </ta-card>
    <ta-card>
      <ta-tag-select style="margin-bottom:5px" title="添加BOM" :data="tagData" @change="getResult" size="small" />
      <ta-table
        :columns="tableColumns"
        :dataSource="mainBOM"
        :scroll="{ y: 354 }"
        size="middle"
      >
        <a slot="action" slot-scope="text, record" @click="select(record)">选择</a>
        <span slot="isputbom" slot-scope="text">
          <ta-tag v-if="text == '是'" type="success">是</ta-tag>
          <ta-tag v-else type="danger">否</ta-tag>
        </span>
      </ta-table>
      <ta-pagination
        style="text-align: right; margin-top: 10px"
        :dataSource.sync="mainBOM"
        :params="userPageParams"
        url="basicInfo/queryMainListPage"
        ref="gridPager"
      />
      <add-children
        :visible="visible"
        v-if="visible"
        :oneMainBom="oneMainBom"
        ref="addChildren"
        @hideModal="hideModal"
        @addSuccess="addSuccess"
      />
    </ta-card>
  </div>
</template>
<script>
import EditChildren from "../manageBOM/part/editChildren.vue";
import addChildren from "./part/addChildren.vue";
const tableColumns = [
  {
    title: "科目编号",
    dataIndex: "id",
    width: "15%",
  },
  {
    title: "品名",
    dataIndex: "name",
    width: "15%",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "14%",
  },
  {
    title: "是否已添加",
    dataIndex: "isputbom",
    width: "14%",
    scopedSlots: { customRender: "isputbom" },
  },
  {
    title: "分类名称",
    dataIndex: "type",
    width: "15%",
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "14%",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "inputBOM",
  components: { addChildren, EditChildren },
  data() {
    return {
      mainBOM: [],
      oneMainBom: {},
      isputbom: "",
      visible: false,
      tagData: [
        { value: "1", label: "已添加" },
        { value: "0", label: "未添加" },
      ],
      tableColumns,
    };
  },
  mounted() {
    this.$refs.gridPager.loadData((data) => {
      //这里是查询后的回调,可以不实现
    });
  },
  methods: {
    addSuccess() {
      this.visible = false;
      this.$refs.gridPager.loadData((data) => {});
    },
    hideModal() {
      this.visible = false;
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form1.resetFields();
      this.$refs.gridPager.loadData((data) => {});
    },
    userPageParams() {
      let wlQueryVo = this.form1.getFieldsValue();
      if (this.isputbom !== "") {
        wlQueryVo.isputbom = this.isputbom;
      }
      //这里返回的对象是分页要一同提交的参数
      return wlQueryVo;
    },
    select(record) {
      if (record.isputbom == "是") {
        this.info();
      } else {
        this.visible = true;
        this.oneMainBom = record;
      }
    },
    info() {
      Modal.info({
        title: "BOM已添加",
        footer: true, // footer:false 与 '' 在此处都可以隐藏footer
        onOk() {},
      });
    },
    queryWLCondition() {
      let data = this.form1.getFieldsValue();
      if (
        data.id !== undefined ||
        data.name !== undefined ||
        data.type !== undefined
      ) {
        this.$refs.gridPager.loadData((data) => {});
      }
    },
    getResult(data) {
      if (data[0] == "0") {
        this.isputbom = "否";
        this.$refs.gridPager.loadData((data) => {});
      } else if (data[0] == "1") {
        this.isputbom = "是";
        this.$refs.gridPager.loadData((data) => {});
      } else {
        this.isputbom = "";
        this.$refs.gridPager.loadData((data) => {});
      }
    },
  },
};
</script>

<style scoped type="text/less">
.top1-text {
  font-size: 10px;
}
</style>
