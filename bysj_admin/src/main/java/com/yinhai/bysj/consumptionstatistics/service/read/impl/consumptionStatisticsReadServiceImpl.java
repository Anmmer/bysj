package com.yinhai.bysj.consumptionstatistics.service.read.impl;

import com.yinhai.bysj.consumptionstatistics.entity.ConsumptionStatistics;
import com.yinhai.bysj.consumptionstatistics.mapper.read.consumptionStatisticsReadMapper;
import com.yinhai.bysj.consumptionstatistics.service.read.consumptionStatisticsReadService;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsInfoVo;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsQueryVo;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;

import javax.annotation.Resource;
import java.util.List;

/**
* 原料消耗统计(ConsumptionStatistics)表服务实现类
*
* @author default
* @since 2020-12-29 14:43:13
*/
@Service
@NoTransactional
public class consumptionStatisticsReadServiceImpl implements consumptionStatisticsReadService {
@Resource
private consumptionStatisticsReadMapper consumptionStatisticsReadMapper;


    /**
    * 根据查询条件查询数据
    *
    * @param consumptionStatisticsQueryVo
    */
    @Override
    public List<ConsumptionStatisticsInfoVo> queryConsumptionStatisticsInfoList(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo) {
        return consumptionStatisticsReadMapper.queryList(consumptionStatisticsQueryVo);
    }

    /**
    * 根据查询条件分页查询数据
    *
    * @param consumptionStatisticsQueryVo
    * @param pageParam
    */
    @Override
    public Page<ConsumptionStatisticsInfoVo> queryConsumptionStatisticsInfoPage(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo, PageParam pageParam) {

        consumptionStatisticsReadMapper.beginPager(pageParam);
        return consumptionStatisticsReadMapper.endPager(consumptionStatisticsReadMapper.queryList(consumptionStatisticsQueryVo));
    }

}
