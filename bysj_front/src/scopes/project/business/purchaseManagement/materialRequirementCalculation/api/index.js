const BASE_URL = "/plan/";
export default {
  addMR(callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "addMR" },
      { successCallback: (data) => callBack(data) }
    );
  },
};
