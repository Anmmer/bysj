const BASE_URL = "/consumptionStatistics/";
export default {
  addConsumptionStatisticsInfo(data, callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "addConsumptionStatisticsInfo", data: data },
      { successCallback: (data) => callBack(data) }
    );
  },
};
