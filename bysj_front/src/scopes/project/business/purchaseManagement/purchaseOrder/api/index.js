const BASE_URL = "/purchaseOrder/";

export default {
  addOrder(callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "addPurchaseOrderInfo" },
      { successCallback: (data) => callBack(data) }
    );
  },
};
