package com.yinhai.bysj.consumptionstatistics.mapper.read;

import com.yinhai.bysj.consumptionstatistics.entity.ConsumptionStatistics;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsQueryVo;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsInfoVo;
import org.apache.ibatis.annotations.Param;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* 原料消耗统计(ConsumptionStatistics)表数据库访问层
*
* @author default
* @since 2020-12-29 14:43:13
*/
public interface consumptionStatisticsReadMapper  extends Ta404SupportMapper{

    /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
    ConsumptionStatisticsInfoVo queryById(String id);

    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<ConsumptionStatisticsInfoVo> queryList(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo);

}