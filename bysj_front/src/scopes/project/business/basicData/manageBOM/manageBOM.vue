<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">产品结构</span>
        <span class="top1-text">(BOM)</span>
        <span class="top2-text">管理</span>
      </ta-card>
    </div>
    <div style="padding: 13.5px; width: 75%; background-color: #f0f2f5">
      <ta-card style="height: 60px">
        <div style="position: relative; top: 3px">
          <ta-form
            layout="horizontal"
            :formLayout="true"
            :autoFormCreate="
              (form) => {
                this.form = form;
              }
            "
          >
            <ta-form-item label="货品编号" fieldDecoratorId="id" :span="8">
              <ta-input style="width: 200px" />
            </ta-form-item>
            <ta-form-item label="货品名称" fieldDecoratorId="name" :span="8">
              <ta-input placeholder="" style="width: 200px" />
            </ta-form-item>
            <ta-form-item :span="8">
              <ta-button @click="queryWLCondition" type="primary"
                >查询</ta-button
              >
              <ta-button @click="resetValue" type="primary">重置</ta-button>
            </ta-form-item>
          </ta-form>
        </div>
      </ta-card>
      <div style="padding: 13.5px 0px; height: 475px">
        <ta-card>
          <ta-table
            :columns="tableColumns"
            :dataSource="mainBOM"
            :haveSn="true"
            :scroll="{ y: 348 }"
          >
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
                <ta-icon
                  slot="icon"
                  type="question-circle-o"
                  style="color: red"
                />
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
      </div>
    </div>
    <div
      style="
        position: absolute;
        right: 0px;
        top: 52px;
        width: 25%;
        padding: 14px 13.5px 13px 0px;
        background-color: #f0f2f5;
      "
    >
      <ta-card style="height: 538.5px">
        <p slot="title" style="margin-top: 6px; margin-bottom: 7px">
          BOM展开图
        </p>
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
  </div>
</template>
<script>
import $api from "./api/index";
import editChildren from "./part/editChildren";
const tableColumns = [
  {
    title: "科目编号",
    dataIndex: "id",
    width: "15%",
    align: "center",
  },
  {
    title: "品名",
    dataIndex: "name",
    width: "23%",
    align: "center",
  },
  {
    title: "单位",
    dataIndex: "unit",
    width: "11%",
    align: "center",
  },
  {
    title: "录入时间",
    dataIndex: "bomdate",
    width: "20%",
    align: "center",
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