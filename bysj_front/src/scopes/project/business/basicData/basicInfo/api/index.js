const BASE_URL = "/basicInfo/";
export default {
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
  addWL(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "addWlInfo", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  editWL(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "editWlInfo", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  deleteWLById(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "removeWlById", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  queryAllWL(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "queryWlInfoList", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  queryGYS(data, callBack) {
    Base.submit(
      null,
      { url: "/purchaseManagement/queryGysInfoList", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  queryWLById(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "queryWlInfoById", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
};
