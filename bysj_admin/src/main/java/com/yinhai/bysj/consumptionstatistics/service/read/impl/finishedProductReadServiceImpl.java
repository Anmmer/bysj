package com.yinhai.bysj.consumptionstatistics.service.read.impl;

import com.yinhai.bysj.consumptionstatistics.entity.FinishedProduct;
import com.yinhai.bysj.consumptionstatistics.mapper.read.finishedProductReadMapper;
import com.yinhai.bysj.consumptionstatistics.service.read.finishedProductReadService;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductQueryVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanQueryVo;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;

import javax.annotation.Resource;
import java.util.List;

/**
* 已生产产成品(FinishedProduct)表服务实现类
*
* @author default
* @since 2020-12-25 17:09:57
*/
@Service
@NoTransactional
public class finishedProductReadServiceImpl implements finishedProductReadService {
@Resource
private finishedProductReadMapper finishedProductReadMapper;

    /**
    * 通过ID查询单条数据
    *
    * @param inde 主键
    * @return 实例对象
    */
    @Override
    public FinishedProductInfoVo queryFinishedProductInfoByInde(Integer inde) {
    return this.finishedProductReadMapper.queryById(inde);
    }

    /**
    * 根据查询条件查询数据
    *
    * @param finishedProductQueryVo
    */
    @Override
    public List<FinishedProductInfoVo> queryFinishedProductInfoList(FinishedProductQueryVo finishedProductQueryVo) {
        return finishedProductReadMapper.queryList(finishedProductQueryVo);
    }

    /**
    * 根据查询条件分页查询数据
    *
    * @param finishedProductQueryVo
    * @param pageParam
    */
    @Override
    public Page<FinishedProductInfoVo> queryFinishedProductInfoPage(FinishedProductQueryVo finishedProductQueryVo, PageParam pageParam) {

        finishedProductReadMapper.beginPager(pageParam);
        return finishedProductReadMapper.endPager(finishedProductReadMapper.queryList(finishedProductQueryVo));
    }

    @Override
    public Page<PlanInfoVo> queryNumListPage(PlanQueryVo planQueryVo, PageParam pageParam) {
        finishedProductReadMapper.beginPager(pageParam);
        return finishedProductReadMapper.endPager(finishedProductReadMapper.queryNumList(planQueryVo));
    }

    @Override
    public PlanInfoVo querySunNumById(PlanQueryVo planQueryVo) {
        return finishedProductReadMapper.querySunNumById(planQueryVo);
    }



}
