const BASE_URL = "/purchaseOrder/";

export default {
  orderArrival(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "editPurchaseOrderInfo", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  batchOrderArrival(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "batchOrderArrival", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
};
