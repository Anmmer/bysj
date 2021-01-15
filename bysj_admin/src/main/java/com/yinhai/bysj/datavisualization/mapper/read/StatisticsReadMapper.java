package com.yinhai.bysj.datavisualization.mapper.read;

import com.yinhai.bysj.datavisualization.entity.Consumption;
import com.yinhai.bysj.datavisualization.entity.Limit;
import com.yinhai.bysj.datavisualization.entity.PieChart;
import com.yinhai.bysj.datavisualization.entity.Total;

import java.util.List;

public interface StatisticsReadMapper {

    List<Total> queryMaterialConsum(Limit limit);

    List<PieChart> queryCountByType();

    List<Total> queryMaterialMoney(Limit limit);

    List<Consumption> queryProduct3DChart(Limit limit);

    List<PieChart> funnelSupplierChart(Limit limit);

    List<Consumption> productNum(Limit limit);

    List<Total> queryProductByMonth(Limit limit);
}
