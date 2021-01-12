package com.yinhai.bysj.purchasemanagement.service.read;

import com.yinhai.bysj.purchasemanagement.entity.Plan;
import com.yinhai.bysj.purchasemanagement.vo.MrInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.MrQueryVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanQueryVo;
import java.util.List;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;


/**
* 生产计划(Plan)表服务接口
*
* @author default
* @since 2020-12-14 14:43:45
*/
public interface PlanReadService {

    /**
    * 通过ID查询单条数据
    *
    * @param inde 主键
    * @return 实例对象
    */
    PlanInfoVo queryPlanInfoById(Integer inde);

    /**
    * 根据查询条件查询数据
    *
    * @param planQueryVo
    */
    List<PlanInfoVo> queryPlanInfoList(PlanQueryVo planQueryVo);

    /**
    * 根据查询条件分页查询数据
    *
    * @param planQueryVo
    * @param pageParam
    */
    Page<PlanInfoVo> queryPlanInfoPage(PlanQueryVo planQueryVo, PageParam pageParam);

    Page<MrInfoVo> queryMRPage(MrQueryVo mrQueryVo, PageParam pageParam);
}
