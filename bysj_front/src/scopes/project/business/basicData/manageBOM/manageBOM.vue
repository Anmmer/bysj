<template>
  <div>
    <ta-card>
      <template slot="title">
        <span>产品结构</span>
        <span class="top1-text">(BOM)</span>
        <span>管理</span>
      </template>
      <div style="
        width: 75%;
      ">
        <ta-form
          style="margin-top:10px"
          layout="horizontal"
          :formLayout="true"
          :autoFormCreate="
              (form) => {
                this.form = form;
              }
            "
        >
          <ta-form-item label="货品编号" fieldDecoratorId="id" :span="7">
            <ta-input style="width: 180px" maxlength="20" placeholder="请输入" />
          </ta-form-item>
          <ta-form-item label="货品名称" fieldDecoratorId="name" :span="7">
            <ta-input placeholder="请输入" style="width: 180px" maxlength="20" />
          </ta-form-item>
          <ta-form-item :span="7">
            <ta-button @click="queryWLCondition" type="primary">
              <ta-icon type="search" />查询
            </ta-button>
            <ta-button @click="resetValue" type="primary">
              <ta-icon type="reload" />重置
            </ta-button>
          </ta-form-item>
        </ta-form>
      </div>
    </ta-card>
    <ta-card style="
        width: 75%;
      ">
      <ta-table :columns="tableColumns" :dataSource="mainBOM" :scroll="{ y: 382 }" size="middle">
        <template slot="action" slot-scope="text, record">
          <a @click="openModal(record)">更改BOM</a>
          <ta-divider type="vertical" />
          <a @click="openBOM(record)">展开BOM</a>
          <ta-divider type="vertical" />
          <ta-popconfirm
            title="是否确认删除?"
            @confirm="deleteBomByMainId(record)"
            cancelText="取消"
            okText="删除"
            okType="danger"
          >
            <ta-icon slot="icon" type="question-circle-o" style="color: red" />
            <a>删除</a>
          </ta-popconfirm>
        </template>
      </ta-table>
      <ta-pagination
        style="text-align: right; margin-top: 10px"
        :dataSource.sync="mainBOM"
        :params="userPageParams"
        url="basicInfo/queryMainListPage"
        ref="gridPager"
      />
    </ta-card>
    <ta-card
      style="
        position: absolute;
        right: 0px;
        top: 120px;
        width: 25%;
        height:495px;
      "
    >
      <template slot="title">BOM展开图</template>
      <div>
        <ta-e-tree
          :data="tree"
          node-key="id"
          :props="defaultProps"
          :highlight-current="true"
          :default-expand-all="true"
        >
          <span slot-scope="{ node }">
            <span>{{ node.label }}</span>
          </span>
        </ta-e-tree>
      </div>
    </ta-card>
    <edit-children
      :visible="visible"
      v-if="visible"
      :oneMainBom="oneMainBom"
      @hideModal="hideModal"
    />
  </div>
</template>
<script>
import $api from "./api/index";
import editChildren from "./part/editChildren.vue";
const tableColumns = [
  {
    title: "科目编号",
    dataIndex: "id",
    width: "18%",
  },
  {
    title: "品名",
    dataIndex: "name",
    width: "22%",
  },
  {
    title: "单位",
    align: "center",
    dataIndex: "unit",
    width: "11%",
  },
  {
    title: "录入时间",
    align: "center",
    dataIndex: "bomdate",
    width: "18%",
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "28%",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "manageBOM",
  components: { editChildren },
  data() {
    return {
      tree: [],
      visible: false,
      tableColumns,
      oneMainBom: [],
      mainBOM: [],
      defaultProps: {
        id: "id",
        children: "children",
        label: "label",
      },
    };
  },
  mounted() {
    this.$refs.gridPager.loadData((data) => {
      //这里是查询后的回调,可以不实现
      this.openBOM(data.data.pageBean.list[0]);
    });
  },
  methods: {
    userPageParams() {
      let wlQueryVo = this.form.getFieldsValue();
      wlQueryVo.isputbom = "是";
      //这里返回的对象是分页要一同提交的参数
      return wlQueryVo;
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form.resetFields();
      this.$refs.gridPager.loadData((data) => {});
    },
    openModal(record) {
      this.visible = true;
      this.oneMainBom = record;
    },
    hideModal() {
      this.visible = false;
      this.openBOM(this.oneMainBom);
    },
    queryWLCondition() {
      let data = this.form.getFieldsValue();
      if (data.id !== undefined || data.name !== undefined) {
        this.$refs.gridPager.loadData((data) => {
          this.openBOM(data.data.pageBean.list[0]);
        });
      }
    },
    queryWL() {
      this.$refs.gridPager.loadData((data) => {});
    },
    openBOM(obj) {
      this.tree = [];
      $api.queryBomTree({ id: obj.id }, (result) => {
        result.data.bomTree.label = obj.id + "[" + obj.name + "]";
        this.tree.push(result.data.bomTree);
      });
    },
    deleteBomByMainId(record) {
      $api.removeBomById({ mid: record.id }, (result) => {
        this.$message.success("删除成功！");
        this.$refs.gridPager.loadData((data) => {
          //这里是查询后的回调,可以不实现
          this.openBOM(data.data.pageBean.list[0]);
        });
      });
    },
  },
};
</script>
<style scoped type="text/less">
.top1-text {
  font-size: 10px;
}
</style>