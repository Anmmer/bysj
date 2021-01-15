package com.yinhai.bysj.consumptionstatistics.service.read;

import com.yinhai.bysj.consumptionstatistics.entity.FinishedProduct;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductQueryVo;
import java.util.List;

import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanQueryVo;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;


/**
* 已生产产成品(FinishedProduct)表服务接口
*
* @author default
* @since 2020-12-25 17:09:57
*/
public interface finishedProductReadService {

    /**
    * 通过ID查询单条数据
    *
    * @param inde 主键
    * @return 实例对象
    */
    FinishedProductInfoVo queryFinishedProductInfoByInde(Integer inde);

    /**
    * 根据查询条件查询数据
    *
    * @param finishedProductQueryVo
    */
    List<FinishedProductInfoVo> queryFinishedProductInfoList(FinishedProductQueryVo finishedProductQueryVo);

    /**
    * 根据查询条件分页查询数据
    *
    * @param finishedProductQueryVo
    * @param pageParam
    */
    Page<FinishedProductInfoVo> queryFinishedProductInfoPage(FinishedProductQueryVo finishedProductQueryVo, PageParam pageParam);

    Page<PlanInfoVo> queryNumListPage(PlanQueryVo planQueryVo,PageParam pageParam);

    PlanInfoVo queryNumByInde(PlanQueryVo planQueryVo);
}
