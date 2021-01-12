package com.yinhai.bysj.consumptionstatistics.mapper.write;

import com.yinhai.bysj.consumptionstatistics.entity.ConsumptionStatistics;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsQueryVo;

import java.util.List;

/**
* 原料消耗统计(ConsumptionStatistics)表数据库访问层
*
* @author default
* @since 2020-12-29 14:43:13
*/
public interface consumptionStatisticsWriteMapper {
    /**
    * 新增数据
    *
    * @return 影响行数
    */
    int insert(List<ConsumptionStatistics> consumptionStatisticsList);

    /**
    * 修改数据
    *
    * @param consumptionStatistics 实例对象
    * @return 影响行数
    */
    int update(ConsumptionStatistics consumptionStatistics);

    /**
    * 通过时间物理删除数据
    *
    * @param consumptionStatisticsQueryVo 主键
    * @return 影响行数
    */
    int deleteByDate(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo);

    /**
    * 通过主键逻辑删除数据
    *
    * @param id 主键
    * @return 影响行数
    */
    int updateToDestroyById(String id);

}
