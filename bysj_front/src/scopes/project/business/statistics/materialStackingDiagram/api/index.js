const BASE_URL = "/Statistics/";

export default {
  getData(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "materialConsumData", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  getMoneyData(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "materialMoneyData", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  materielPieChart(callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "materielPieChart" },
      { successCallback: (data) => callBack(data) }
    );
  },
  getProduct3DChart(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "product3DChart", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  getFunnelSupplierData(callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "funnelSupplierData" },
      { successCallback: (data) => callBack(data) }
    );
  },
  getProductAmountData(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "productAmountChartData", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
  getProductSelfChartData(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "productSelfChartData", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
};
