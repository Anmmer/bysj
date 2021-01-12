package com.yinhai.bysj.datavisualization.service.read;

import com.yinhai.bysj.datavisualization.entity.Limit;
import com.yinhai.bysj.datavisualization.entity.PieChart;
import com.yinhai.bysj.datavisualization.entity.Total;

import java.util.List;

public interface StatisticsService {

    List<Total> queryMaterialConsum(Limit limit);

    List<PieChart> queryCountByType();
}
