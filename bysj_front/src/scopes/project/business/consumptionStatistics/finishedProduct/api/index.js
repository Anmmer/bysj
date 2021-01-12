const BASE_URL = "/finishedProduct/";
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
  removeFinishedProductById(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "removeFinishedProductById", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  editFinishedProductInfo(form, data, callBack) {
    Base.submit(
      form,
      {
        url: BASE_URL + "editFinishedProductInfo",
        data: data,
        autoValid: true,
      },
      { successCallback: (data) => callBack(data) }
    );
  },
  addFinishedProductInfo(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "addFinishedProductInfo", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
};
