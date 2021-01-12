const BASE_URL = "/plan/";
export default {
  editPlanInfo(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "editPlanInfo", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  addPlanInfo(form, data, callBack) {
    Base.submit(
      form,
      { url: BASE_URL + "addPlanInfo", data: data, autoValid: true },
      { successCallback: (data) => callBack(data) }
    );
  },
  removePlanById(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "removePlanById", data: data },
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
