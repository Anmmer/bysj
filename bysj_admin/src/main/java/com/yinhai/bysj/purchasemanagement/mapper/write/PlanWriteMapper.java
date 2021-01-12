package com.yinhai.bysj.purchasemanagement.mapper.write;

import com.yinhai.bysj.purchasemanagement.entity.Plan;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;

import java.util.List;

/**
* 生产计划(Plan)表数据库访问层
*
* @author default
* @since 2020-12-14 14:43:44
*/
public interface PlanWriteMapper {
    /**
    * 新增数据
    *
    * @param plan 实例对象
    * @return 影响行数
    */
    int insert(Plan plan);

    /**
    * 修改数据
    *
    * @param plan 实例对象
    * @return 影响行数
    */
    int update(Plan plan);

    int batchUpdate(List<PlanInfoVo> list);

    /**
    * 通过主键物理删除数据
    *
    * @param inde 主键
    * @return 影响行数
    */
    int deleteById(Integer inde);

    /**
    * 通过主键逻辑删除数据
    *
    * @param index 主键
    * @return 影响行数
    */
    int updateToDestroyById(Integer index);

}
