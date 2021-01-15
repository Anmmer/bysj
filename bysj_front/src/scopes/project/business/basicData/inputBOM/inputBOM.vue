<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">产品结构</span>
        <span class="top1-text">(BOM)</span>
        <span class="top2-text">资料录入</span>
      </ta-card>
    </div>
    <div style="padding: 13.5px 13.5px; width: 100%; background-color: #f0f2f5">
      <ta-card style="height: 60px">
        <div style="position: relative; top: 3px">
          <ta-form
            layout="horizontal"
            :formLayout="true"
            :autoFormCreate="
              (form) => {
                this.form1 = form;
              }
            "
          >
            <ta-form-item label="货品编号" fieldDecoratorId="id" :span="6">
              <ta-input style="width: 200px" />
            </ta-form-item>
            <ta-form-item label="货品名称" fieldDecoratorId="name" :span="6">
              <ta-input placeholder="" style="width: 200px" />
            </ta-form-item>
            <ta-form-item label="分类名称" fieldDecoratorId="type" :span="6">
              <ta-select collectionType="PRODUCTIONTYPE" style="width: 180px"/>
            </ta-form-item>
            <ta-form-item :span="6">
              <ta-button @click="queryWLCondition" type="primary"
                >查询</ta-button
              >
              <ta-button @click="resetValue" type="primary">重置</ta-button>
            </ta-form-item>
          </ta-form>
        </div>
      </ta-card>
      <div style="padding: 13.5px 0px; height: 477px">
        <ta-card>
          <div style="height: 37px; margin-top: 5px; margin-bottom: 0px">
            <ta-tag-select
              title="添加主件"
              :data="tagData"
              @change="getResult"
              size="small"
            />
          </div>
          <ta-table
            :columns="tableColumns"
            :dataSource="mainBOM"
            :haveSn="true"
            :scroll="{ y: 307.5 }"
          >
            <a slot="action" slot-scope="text, record" @click="select(record)"
              >选择</a
            >
            <span slot="isputbom" slot-scope="text">
              <ta-tag v-if="text == '是'" type="success">是</ta-tag>
              <ta-tag v-else type="danger">否</ta-tag>
            </span></ta-table
          >
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
    </div>
  </div>
</template>
<script>
import EditChildren from "../manageBOM/part/editChildren.vue";
import addChildren from "./part/addChildren.vue";
const tableColumns = [
  {
    title: "科目编号",
    dataIndex: "id",
    width: "18%",
    align: "center",
  },
  {
    title: "品名",
    dataIndex: "name",
    width: "18%",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "14%",
    align: "center",
  },
  {
    title: "是否已添加",
    dataIndex: "isputbom",
    width: "14%",
    align: "center",
    scopedSlots: { customRender: "isputbom" },
  },
  {
    title: "分类名称",
    dataIndex: "type",
    width: "15%",
    align: "center",
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
      if (data.id !== undefined || data.name !== undefined || data.type!==undefined) {
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
.top-text {
  font-weight: 550;
  font-size: 20px;
  margin-left: 10px;
}
.top1-text {
  font-weight: 550;
  font-size: 15px;
}
.top2-text {
  font-weight: 550;
  font-size: 20px;
}
</style>
