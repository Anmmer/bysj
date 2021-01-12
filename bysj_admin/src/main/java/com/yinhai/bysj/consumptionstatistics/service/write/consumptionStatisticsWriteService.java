package com.yinhai.bysj.consumptionstatistics.service.write;

import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsAddVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;

import java.util.List;

/**
* 原料消耗统计(ConsumptionStatistics)表服务接口
*
* @author default
* @since 2020-12-29 14:43:13
*/
public interface consumptionStatisticsWriteService {
    /**
    * 新增数据
    *
    *
    * @return 实例对象
    */
   int addConsumptionStatisticsInfo(List<ConsumptionStatisticsAddVo> list, List<FinishedProductInfoVo> finishedProductInfoVoList);



}
