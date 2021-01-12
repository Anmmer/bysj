package com.yinhai.bysj.purchasemanagement.service.write;

import com.yinhai.bysj.purchasemanagement.entity.Mr;
import com.yinhai.bysj.purchasemanagement.entity.Plan;
import com.yinhai.bysj.purchasemanagement.vo.MrAddVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanAddVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanEditVo;
import java.util.List;

/**
* 生产计划(Plan)表服务接口
*
* @author default
* @since 2020-12-14 14:43:45
*/
public interface PlanWriteService {
    /**
    * 新增数据
    *
    * @param planAddVo 实例对象
    * @return 实例对象
    */
    Plan addPlanInfo(PlanAddVo planAddVo);

    /**
    * 修改数据
    *
    * @param planEditVo 实例对象
    * @return 实例对象
    */
    Plan editPlanInfo(PlanEditVo planEditVo);

    /**
    * 通过主键物理删除数据
    *
    * @param inde 主键
    * @return 是否成功
    */
    int removePlanById(Integer inde);


    /**
    * 通过主键逻辑删除数据
    *
    * @param index 主键
    * @return 是否成功
    */
    int logicRemovePlanById(Integer index);

    /**
     * 新增数据
     *
     * @param mrAddVo 实例对象
     * @return 实例对象
     */
    int addMrInfo(List<MrAddVo> mrAddVo,List<PlanInfoVo> list);


}
