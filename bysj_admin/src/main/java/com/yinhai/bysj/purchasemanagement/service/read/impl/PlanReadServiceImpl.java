package com.yinhai.bysj.purchasemanagement.service.read.impl;

import com.yinhai.bysj.purchasemanagement.mapper.read.MRReadMapper;
import com.yinhai.bysj.purchasemanagement.mapper.read.PlanReadMapper;
import com.yinhai.bysj.purchasemanagement.service.read.PlanReadService;
import com.yinhai.bysj.purchasemanagement.vo.MrInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.MrQueryVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;

import javax.annotation.Resource;
import java.util.List;

/**
 * 生产计划(Plan)表服务实现类
 *
 * @author default
 * @since 2020-12-14 14:43:45
 */
@Service
@NoTransactional
public class PlanReadServiceImpl implements PlanReadService {
    @Resource
    private PlanReadMapper planReadMapper;
    @Autowired
    private MRReadMapper mrReadMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param inde 主键
     * @return 实例对象
     */
    @Override
    public PlanInfoVo queryPlanInfoById(Integer inde) {
        return this.planReadMapper.queryById(inde);
    }

    /**
     * 根据查询条件查询数据
     *
     * @param planQueryVo
     */
    @Override
    public List<PlanInfoVo> queryPlanInfoList(PlanQueryVo planQueryVo) {
        return planReadMapper.queryList(planQueryVo);
    }

    /**
     * 根据查询条件分页查询数据
     *
     * @param planQueryVo
     * @param pageParam
     */
    @Override
    public Page<PlanInfoVo> queryPlanInfoPage(PlanQueryVo planQueryVo, PageParam pageParam) {

        planReadMapper.beginPager(pageParam);
        return planReadMapper.endPager(planReadMapper.queryList(planQueryVo));
    }

    public Page<MrInfoVo> queryMRPage(MrQueryVo mrQueryVo, PageParam pageParam) {

        mrReadMapper.beginPager(pageParam);
        return mrReadMapper.endPager((mrReadMapper.queryList(mrQueryVo)));
    }
}
