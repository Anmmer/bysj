const BASE_URL = "/consumptionStatistics/";
export default {
  addConsumptionStatisticsInfo(callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "addConsumptionStatisticsInfo" },
      { successCallback: (data) => callBack(data) }
    );
  },
};
