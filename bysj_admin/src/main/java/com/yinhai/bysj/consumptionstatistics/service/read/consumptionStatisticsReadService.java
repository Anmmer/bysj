package com.yinhai.bysj.consumptionstatistics.service.read;

import com.yinhai.bysj.consumptionstatistics.entity.ConsumptionStatistics;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsInfoVo;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsQueryVo;
import java.util.List;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;


/**
* 原料消耗统计(ConsumptionStatistics)表服务接口
*
* @author default
* @since 2020-12-29 14:43:13
*/
public interface consumptionStatisticsReadService {

    /**
    * 根据查询条件查询数据
    *
    * @param consumptionStatisticsQueryVo
    */
    List<ConsumptionStatisticsInfoVo> queryConsumptionStatisticsInfoList(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo);

    /**
    * 根据查询条件分页查询数据
    *
    * @param consumptionStatisticsQueryVo
    * @param pageParam
    */
    Page<ConsumptionStatisticsInfoVo> queryConsumptionStatisticsInfoPage(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo, PageParam pageParam);
}
