package com.yinhai.bysj.datavisualization.service.read.impl;

import com.yinhai.bysj.datavisualization.entity.Limit;
import com.yinhai.bysj.datavisualization.entity.PieChart;
import com.yinhai.bysj.datavisualization.entity.Total;
import com.yinhai.bysj.datavisualization.mapper.read.StatisticsReadMapper;
import com.yinhai.bysj.datavisualization.service.read.StatisticsService;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoTransactional
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsReadMapper statisticsReadMapper;

    @Override
    public List<Total> queryMaterialConsum(Limit limit) {
        return statisticsReadMapper.queryMaterialConsum(limit);
    }

    @Override
    public List<PieChart> queryCountByType() {
        return statisticsReadMapper.queryCountByType();
    }
}
