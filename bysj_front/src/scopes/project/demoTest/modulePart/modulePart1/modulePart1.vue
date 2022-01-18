<template>
  <div>
    <ta-button @click="addOne">新增</ta-button>
    <ta-form :auto-form-create="(form)=>{this.form1 = form}">
      <ta-table :columns="columns" :data-source="tableData" :scroll="{ y: 351 }">
        <span slot="nameTitle">
          input示例
          <ta-icon type="edit" />
        </span>
        <ta-table-edit
          slot="name"
          slot-scope="text, record"
          :edit-form="form1"
          :rules="[{transform: fnTransform},{validator: validateTransform}]"
          type="input"
          :row-editable="true"
        />
        <ta-table-edit
          slot="age"
          slot-scope="text, record"
          :edit-form="form1"
          type="inputNumber"
          :row-editable="true"
        />
        <ta-table-edit
          slot="sex"
          slot-scope="text, record"
          :edit-form="form1"
          type="select"
          :option="CollectionData('SEX')"
          :row-editable="true"
        />
        <ta-table-edit
          slot="date"
          slot-scope="text, record"
          :edit-form="form1"
          type="datePicker"
          :row-editable="true"
        />
        <ta-table-edit
          slot="action"
          slot-scope="text, record"
          :edit-form="form1"
          :data-source="tableData"
          type="rowEdit"
          :before-change="fnBeforeChange"
          :operate-menu="operateMenu"
          @change="changeData"
          @tableChange="fnTableChange"
          @rowDelete="fnRowDelete"
        />
      </ta-table>
    </ta-form>
    <part-component :buttonKey="buttonKey"/>
  </div>
</template>

<script>
import partComponent from "./part/partComponent";

// const name = "张三";

const columns = [
  {
    dataIndex: "name",
    width: 200,
    slots: { title: "nameTitle" },
    scopedSlots: { customRender: "name" },
    overflowTooltip: true,
  },
  {
    title: "inputNumber示例",
    dataIndex: "age",
    width: 200,
    scopedSlots: { customRender: "age" },
  },
  {
    title: "select-单选示例",
    dataIndex: "sex",
    width: 200,
    scopedSlots: { customRender: "sex" },
  },
  {
    title: "日期选择框-datePicker示例",
    dataIndex: "date",
    width: 200,
    scopedSlots: { customRender: "date" },
  },
  {
    title: "操作",
    dataIndex: "action",
    width: 150,
    scopedSlots: { customRender: "action" },
  },
];

const tempData = [];
for (let i = 0; i < 6; i++) {
  tempData.push({
    key: i.toString(),
    name: `Name ${i}`,
    age: i,
    sex: `${i % 3}`,
    date: "2019-05-10"
  });
}
export default {
  name: "modulePart1",
  components: { partComponent },
  data() {
    return {
      tableData: [],
      columns,
      buttonKey:"按钮",
      total:0,
      index:-1,
      operateMenu: [
        {
          editType: "edit",
          name: "编辑按钮",
          icon: "edit",
          style: { color: "#2f54eb" },
          onClick: (record, index) => {                  
          },
        },
        {
          editType: "save",
          name: "保存按钮",
          icon: "save",
          style: { color: "#faad14" },
          onClick: (record, index) => {
            this.total = this.tableData.length+1;
            this.index = -1;
          },
        },
        {
          editType: "delete",
          name: "删除按钮",
          icon: "delete",
          style: { color: "#eb2f96" },
          onOk(){
            this.total = this.tableData.length-1;
          },
          onCancel(){
          },
        },
        {
          editType: "cancel",
          name: "取消按钮",
          icon: "close",
          style: { color: "#722ed1" },
          onClick: (record, index) => {
            if(index == this.index){
              this.tableData.splice(index,1);
            }
          },
        },
      ],
    };
  },
  mounted() {
    this.tableData = tempData;
    this.total = this.tableData.length;
  },
  methods: {
    addOne() {
      this.tableData.push({
        key: Math.random(),
        name: "Name" + Math.random(),
        age: Math.random(),
        sex: "0",
        date: "2019-05-10",
        editable: true,
      });
      this.index = this.tableData.length-1;
      this.total = this.tableData.length;
      // console.log(this.tableData)
    },
    fnBeforeChange({ newData, record, rowKey }, callback) {
      // 单元格数据保存前数据处理（newData：返回改变后的行数据、record：该行的行数据、rowKey：该行的rowKey值、callback：回调函数，可判断数据是否正确）
      // 注意，callback 必须被调用
      if (newData.name === "123") {
        callback("name不能为123");
      } else {
        callback();
        // 可在此处对表格数据进行处理
        Object.assign(record, newData);
      }
    },
    changeData({ newData, record, rowKey }) {
      // 将行数据修改为新值（newData：返回改变的值、record：该行的行数据、rowKey：该行的rowKey值）
      // 也可当表格编辑状态关闭后，在此处对表格数据进行处理
      console.log(newData);
      Object.assign(record, newData);
    },
    fnTableChange(dataSource) {
      // 将返回有editable属性的表格数据，赋给表格
      this.tableData = dataSource;
    },
    fnRowDelete(deleteId) {
      // 返回主键id，即rowKey的值
      this.tableData = this.tableData.filter((item) => item.key !== deleteId);
    },
    fnTransform(value) {
      return "transform_" + value;
    },
    validateTransform(rule, value, callback) {
      if (value == "transform_") {
        console.log(value);
        callback("请输入值");
      } else {
        callback();
      }
    },
  },
};
</script>

<style scoped type="text/less">
</style>
