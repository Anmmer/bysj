package com.yinhai.bysj.purchasemanagement.service.write.impl;

import com.yinhai.bysj.purchasemanagement.entity.Mr;
import com.yinhai.bysj.purchasemanagement.entity.Plan;
import com.yinhai.bysj.purchasemanagement.mapper.write.MRWriteMapper;
import com.yinhai.bysj.purchasemanagement.mapper.write.PlanWriteMapper;
import com.yinhai.bysj.purchasemanagement.service.write.PlanWriteService;
import com.yinhai.bysj.purchasemanagement.vo.MrAddVo;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.TaTransactional;
import com.yinhai.ta404.core.service.BaseService;
import com.yinhai.ta404.core.utils.UUIDUtils;
import com.yinhai.ta404.core.exception.AppException;
import org.springframework.beans.BeanUtils;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanAddVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanEditVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 生产计划(Plan)表服务实现类
 *
 * @author default
 * @since 2020-12-14 14:43:46
 */
@Service
@TaTransactional
public class PlanWriteServiceImpl extends BaseService implements PlanWriteService {
    @Resource
    private PlanWriteMapper planWriteMapper;
    @Resource
    private MRWriteMapper mRWriteMapper;

    /**
     * 新增数据
     *
     * @param planAddVo 实例对象
     * @return 实例对象
     */
    @Override
    public Plan addPlanInfo(PlanAddVo planAddVo) {
        Plan plan = new Plan();
        BeanUtils.copyProperties(planAddVo, plan);
        plan.setInputDate(getSysDate());
        int i = planWriteMapper.insert(plan);
        if (i != 1) {
            throw new AppException("未添加任何数据，请重试");
        }
        return plan;
    }

    /**
     * 修改数据
     *
     * @param planEditVo 实例对象
     * @return 实例对象
     */
    @Override
    public Plan editPlanInfo(PlanEditVo planEditVo) {
        Plan plan = new Plan();
        BeanUtils.copyProperties(planEditVo, plan);
        int i = planWriteMapper.update(plan);
        if (i != 1) {
            throw new AppException("未更新任何数据，请重试");
        }
        return plan;
    }

    /**
     * 通过主键物理删除数据
     *
     * @param inde 主键
     * @return 受影响行数
     */
    @Override
    public int removePlanById(Integer inde) {
        int i = this.planWriteMapper.deleteById(inde);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }

    /**
     * 通过主键逻辑删除数据
     *
     * @param index 主键
     * @return 受影响行数
     */
    @Override
    public int logicRemovePlanById(Integer index) {
        int i = this.planWriteMapper.updateToDestroyById(index);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }


    /**
     * 新增数据
     *
     * @param mrAddVo 实例对象
     * @return 实例对象
     */
    @Override
    public int addMrInfo(List<MrAddVo> mrAddVo,List<PlanInfoVo> list) {
        this.planWriteMapper.batchUpdate(list);
        int i = mRWriteMapper.insert(mrAddVo);
        if (i == 0) {
            throw new AppException("未添加任何数据，请重试");
        }
        return i;

    }
}
