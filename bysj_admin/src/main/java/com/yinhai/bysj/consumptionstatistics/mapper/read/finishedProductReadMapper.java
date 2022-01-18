package com.yinhai.bysj.consumptionstatistics.mapper.read;

import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductQueryVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanQueryVo;
import org.apache.ibatis.annotations.Param;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* 已生产产成品(FinishedProduct)表数据库访问层
*
* @author default
* @since 2020-12-25 17:09:56
*/
public interface finishedProductReadMapper  extends Ta404SupportMapper{

    /**
    * 通过ID查询单条数据
    *
    * @param inde 主键
    * @return 实例对象
    */
    FinishedProductInfoVo queryById(Integer inde);

    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<FinishedProductInfoVo> queryList(FinishedProductQueryVo finishedProductQueryVo);

    List<PlanInfoVo> queryNumList(PlanQueryVo planQueryVo);

    PlanInfoVo querySunNumById(PlanQueryVo planQueryVo);


}
