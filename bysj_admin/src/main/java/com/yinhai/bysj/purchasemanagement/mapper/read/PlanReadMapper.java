package com.yinhai.bysj.purchasemanagement.mapper.read;

import com.yinhai.bysj.purchasemanagement.vo.PlanQueryVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* 生产计划(Plan)表数据库访问层
*
* @author default
* @since 2020-12-14 14:43:43
*/
public interface PlanReadMapper  extends Ta404SupportMapper{

    /**
    * 通过ID查询单条数据
    *
    * @param inde 主键
    * @return 实例对象
    */
    PlanInfoVo queryById(Integer inde);

    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<PlanInfoVo> queryList(PlanQueryVo planQueryVo);

}
