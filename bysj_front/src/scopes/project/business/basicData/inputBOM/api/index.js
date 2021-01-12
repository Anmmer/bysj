const BASE_URL = "/bom/";
export default {
  insertBomBatch(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "insertBomBatch", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
};
