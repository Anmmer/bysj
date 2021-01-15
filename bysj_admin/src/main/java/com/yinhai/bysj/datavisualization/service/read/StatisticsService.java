package com.yinhai.bysj.datavisualization.service.read;

import com.yinhai.bysj.datavisualization.entity.*;

import java.util.List;

public interface StatisticsService {

    List<Total> queryMaterialConsum(Limit limit);

    List<PieChart> queryCountByType();

    List<Total> queryMaterialMoney(Limit limit);

    List<Consumption> queryProduct3DChart(Limit limit);

    List<PieChart> funnelSupplierChart(Limit limit);

    List<Consumption> productNum(Limit limit);

    List<Total> queryProductByMonth(Limit limit);
}
