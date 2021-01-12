const BASE_URL = "/basicInfo/";
export default {
  queryRawListPage(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "queryRawListPage", data, data },
      { successCallback: (data) => callBack(data) }
    );
  },
  editBomInfo(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "editWlInfo", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  addRawPrice(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "addRawPrice", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  removeRawPrice(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "editWlInfo", data: data },
      { successCallback: (data) => callBack(data) }
    );
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
};
