<template>
  <div>
    <div style="padding: 0px 5px; background-color: #f0f2f5">
      <ta-card>
        <span class="top-text">货品信息管理</span>
        <span class="top1-text">(产成品、原材料、自制半成品)</span>
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
              <ta-input style="width: 200px" maxLength="20" />
            </ta-form-item>
            <ta-form-item label="货品名称" fieldDecoratorId="name" :span="6">
              <ta-input placeholder="" style="width: 200px" maxLength="20" />
            </ta-form-item>
            <ta-form-item label="供货商" fieldDecoratorId="gysid" :span="6">
              <ta-select style="width: 200px">
                <ta-select-option v-for="s in this.gysList" :key="s.gysid">{{
                  s.gys
                }}</ta-select-option></ta-select
              >
            </ta-form-item>
            <ta-form-item :span="6">
              <ta-button @click="queryWLCondition" type="primary"
                ><ta-icon type="search" />查询</ta-button
              >
              <ta-button @click="resetValue" type="primary"
                ><ta-icon type="reload" />重置</ta-button
              >
            </ta-form-item>
          </ta-form>
        </div>
      </ta-card>
      <div style="padding: 13.5px 0px; height: 475px">
        <ta-card>
          <ta-button
            @click="showDrawer"
            style="margin-bottom: 5px;"
            type="primary"
            ><ta-icon type="plus" />新增</ta-button
          >
          <ta-button
            style="margin-bottom: 5px;"
            @click="exportData"
          >
            导出数据
          </ta-button>
          <ta-table
            :columns="tableColumns"
            :dataSource="wl"
            :haveSn="true"
            :scroll="{ y: 319 }"
            size="middle"
          >
            <template slot="action" slot-scope="text, record">
              <a @click="setId(record.id)">修改</a>
              <ta-divider type="vertical" />
              <ta-popconfirm
                title="是否确认删除?"
                @confirm="RowDelete(record.id)"
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
          <div>
            <ta-pagination
              style="text-align: right; margin-top: 10px"
              :dataSource.sync="wl"
              :params="userPageParams"
              url="basicInfo/queryWlInfoPage"
              ref="gridPager"
            />
          </div>
        </ta-card>
      </div>
    </div>
    <add-info
      @onClose="onClose"
      @addSuccess="addSuccess"
      v-if="visible"
      :visible="visible"
      :gysList="gysList"
      :id="id"
      :type="type"
      width="500"
    />
  </div>
</template>

<script>
import $api from "./api";
import addInfo from "./part/addInfo.vue";

const tableColumns = [
  {
    title: "货品编号",
    dataIndex: "id",
    // align: "center",
    width: "150px",
  },
  {
    dataIndex: "name",
    // align: "center",
    width: "170px",
    title: "货品名称",
  },
  {
    dataIndex: "standard",
    // align: "center",
    width: "180px",
    title: "规格",
  },
  {
    dataIndex: "type",
    // align: "center",
    width: "150px",
    title: "货品类别",
  },
  {
    dataIndex: "unit",
    // align: "center",
    width: "110px",
    title: "单位",
  },
  {
    dataIndex: "gys",
    // align: "center",
    width: "200px",
    title: "供应商",
  },
  {
    title: "操作",
    dataIndex: "action",
    align: "center",
    width: "150px",
    scopedSlots: { customRender: "action" },
  },
];
export default {
  name: "basicInfo",
  components: { addInfo },
  data() {
    return {
      gysList: [],
      wl: [],
      id: "",
      type: 0,
      visible: false,
      tableColumns,
    };
  },
  mounted() {
    this.queryGYS();
    this.$refs.gridPager.loadData((data) => {
      //这里是查询后的回调,可以不实现
    });
  },
  methods: {
    showDrawer() {
      this.visible = true;
    },
    onClose() {
      this.visible = false;
      this.type = 0;
      this.id = "";
    },
    userPageParams() {
      let wlQueryVo = this.form1.getFieldsValue();
      //这里返回的对象是分页要一同提交的参数
      return wlQueryVo;
    },
    resetValue() {
      // 对设置了fieldDecoratorId的表单元素重置值
      this.form1.resetFields();
      this.$refs.gridPager.loadData((data) => {});
    },
    setId(id) {
      this.id = id;
      this.type = 1;
      this.showDrawer();
    },
    addSuccess() {
      this.onClose();
      this.$refs.gridPager.loadData((data) => {});
    },
    RowDelete(deleteId) {
      // 返回主键id，即rowKey的值
      $api.deleteWLById({ id: deleteId }, (result) => {
        this.$message.success("删除成功");
        this.wl = this.wl.filter((item) => item.id !== deleteId);
      });
    },
    queryGYS() {
      $api.queryGYS({}, (result) => {
        this.gysList = result.data.gysList;
      });
    },
    queryWLCondition() {
      let values = this.form1.getFieldsValue();
      if (
        values.id !== undefined ||
        values.name !== undefined ||
        values.gysid != undefined
      ) {
        this.$refs.gridPager.loadData((data) => {});
      }
    },
    exportData() {
      $api.queryAllWL(null, (result) => {
        const data = {
          fileName: "物料基础信息", // 文件名
          // 工作表List
          sheets: [
            {
              // 工作表名字，默认为Sheet加上该工作表在List中的index
              name: "物料信息",
              column: {
                // 简单表头设置如下
                complex: false,
                // 简单表头的列索引
                columns: [
                  { header: "货品编号", key: "id", width: 20 },
                  { header: "货品名称", key: "name", width: 30 },
                  { header: "规格", key: "standard", width: 20 },
                  { header: "货品类别", key: "type", width: 20 },
                  { header: "单位", key: "unit", width: 20 },
                  { header: "供应商", key: "gys", width: 40 },
                ],
              },
              // 表格数据
              rows: result.data.wlList,
            },
          ],
        };
        this.Base.generateExcel(data);
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
</style>
