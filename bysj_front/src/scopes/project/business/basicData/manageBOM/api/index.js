const BASE_URL = "/bom/";
export default {
  buildTree(data, obj) {
    let tree = [];
    tree.push({
      id: 1,
      label: obj.id + "[" + obj.name + "]",
      children: [],
    });
    for (let i = 0; i < data.length; i++) {
      tree[0].children.push({
        id: data[i].id,
        label:
          data[i].id +
          "[" +
          data[i].name +
          "]" +
          "×" +
          data[i].num +
          data[i].unit,
      });
    }
    return tree;
  },
  isObjectValueEqual(a, b) {
    var aProps = Object.getOwnPropertyNames(a);
    for (var i = 0; i < aProps.length; i++) {
      var propName = aProps[i];
      var propA = a[propName];
      var propB = b[propName];
      if (propA !== propB) {
        return false;
      }
    }
    return true;
  },
  queryBomInfoList(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "queryBomInfoList", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  queryBomTree(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "queryBomTree", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  editBomInfo(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "editBomInfo", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  removeBomById(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "removeBomBySid", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  addBomInfo(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "addBomInfo", data: data ,autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  removeBomBySid(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "removeBomBySid", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
};
