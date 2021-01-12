import { data } from "jquery";

const BASE_URL = "/Statistics/";

export default {
  getData(callBack) {
    Base.submit(
      null,
      { url: BASE_URL + "materialConsumData" },
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
};
