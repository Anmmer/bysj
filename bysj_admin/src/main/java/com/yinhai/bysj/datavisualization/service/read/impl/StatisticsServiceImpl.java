package com.yinhai.bysj.datavisualization.service.read.impl;

import com.yinhai.bysj.basicdata.mapper.read.basicInfoReadMapper;
import com.yinhai.bysj.basicdata.service.read.BomReadService;
import com.yinhai.bysj.datavisualization.entity.*;
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
    @Autowired
    private basicInfoReadMapper basicInfoReadMapper;
    @Autowired
    private BomReadService bomReadService;

    @Override
    public List<Total> queryMaterialConsum(Limit limit) {
        return statisticsReadMapper.queryMaterialConsum(limit);
    }

    @Override
    public List<PieChart> queryCountByType() {
        return statisticsReadMapper.queryCountByType();
    }

    @Override
    public List<Total> queryMaterialMoney(Limit limit) {
        return statisticsReadMapper.queryMaterialMoney(limit);
    }

    @Override
    public List<Consumption> queryProduct3DChart(Limit limit) {
        return statisticsReadMapper.queryProduct3DChart(limit);
    }

    @Override
    public List<PieChart> funnelSupplierChart(Limit limit) {
        return statisticsReadMapper.funnelSupplierChart(limit);
    }

    @Override
    public List<Consumption> productNum(Limit limit) {
        return statisticsReadMapper.productNum(limit);
    }

    @Override
    public List<Total> queryProductByMonth(Limit limit) {
        return statisticsReadMapper.queryProductByMonth(limit);
    }
}
