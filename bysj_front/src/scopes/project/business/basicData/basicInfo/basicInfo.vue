<template>
  <div>
    <ta-card>
      <template slot="title">
        <span class="top-text">货品信息管理</span>
        <span class="top1-text">(原材料、辅助材料、自制半成品、产成品)</span>
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
          <ta-input placeholder="请输入" style="width: 180px" maxlength="20" />
        </ta-form-item>
        <ta-form-item label="供货商" fieldDecoratorId="gysid" :span="5">
          <ta-select style="width: 180px" placeholder="全部">
            <ta-select-option v-for="s in this.gysList" :key="s.gysid">
              {{
              s.gys
              }}
            </ta-select-option>
          </ta-select>
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
      <ta-button style="margin-bottom:5px" @click="showDrawer" type="primary">
        <ta-icon type="plus" />新&nbsp;增
      </ta-button>

      <ta-popconfirm
        style="position: absolute;right:53px"
        title="确认导出?"
        okText="是"
        cancelText="否"
        @confirm="exportData"
      >
        <ta-button>
          <ta-icon type="cloud-download" />批量导出
        </ta-button>
      </ta-popconfirm>

      <ta-table
        :customHeaderRow="fnCustomHeaderRow"
        :columns="tableColumns"
        :dataSource="wl"
        :scroll="{ y: 350 }"
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
            <ta-icon slot="icon" type="question-circle-o" style="color: red" />
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
    <add-info
      @onClose="onClose"
      @addSuccess="addSuccess"
      v-if="visible"
      :visible="visible"
      :gysList="gysList"
      :id="id"
      :type="type"
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
    fnCustomHeaderRow() {
      return {
        // style: {
        //   fontSize:'14.5px'
        // }
      };
    },
  },
};
</script>
<style type="text/less">
.top1-text {
  font-size: 10px;
}
.mg-l12 {
  margin-left: 11px;
}
</style>
