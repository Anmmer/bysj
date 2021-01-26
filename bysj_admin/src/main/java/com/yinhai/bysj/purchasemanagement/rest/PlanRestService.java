package com.yinhai.bysj.purchasemanagement.rest;

import com.yinhai.bysj.basicdata.common.BomTreeNode;
import com.yinhai.bysj.basicdata.service.read.BomReadService;
import com.yinhai.bysj.purchasemanagement.entity.Plan;
import com.yinhai.bysj.purchasemanagement.service.read.PlanReadService;
import com.yinhai.bysj.purchasemanagement.service.write.PlanWriteService;
import com.yinhai.bysj.purchasemanagement.vo.*;
import com.yinhai.ta404.core.service.time.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.validate.annotation.V;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 生产计划(Plan)表控制层
 *
 * @author default
 * @since 2020-12-14 14:43:46
 */
@Validated
@RestService("plan")
public class PlanRestService extends BaseRestService {
    /**
     * 服务对象
     */
    @Resource
    private PlanReadService planReadService;
    @Resource
    private PlanWriteService planWriteService;
    @Autowired
    private BomReadService bomReadService;
    @Autowired
    private TimeService timeService;

    /**
     * 新增数据
     *
     * @param planAddVo
     */
    @PostMapping("addPlanInfo")
    public void addPlanInfo(@Valid PlanAddVo planAddVo) {
        planWriteService.addPlanInfo(planAddVo);
    }

    /**
     * 通过主键物理删除数据
     *
     * @param inde 主键
     */
    @PostMapping("removePlanById")
    public void removePlanById(@V({"notnull"}) Integer inde) {
        planWriteService.removePlanById(inde);
    }

    /**
     * 通过主键逻辑删除数据
     *
     * @param index 主键
     */
    @PostMapping("removePlan")
    public void logicRemovePlan(@V({"notnull"}) Integer index) {
        planWriteService.logicRemovePlanById(index);
    }

    /**
     * 编辑数据
     *
     * @param planEditVo
     */
    @PostMapping("editPlanInfo")
    public void editPlanInfo(@Valid PlanEditVo planEditVo) {
        PlanInfoVo planInfoVo = planReadService.queryPlanInfoById(planEditVo.getInde());
        if (planInfoVo.getIsCompute().equals("是")) {
            setData("message", "已计算，无法操作");
        } else {
            planWriteService.editPlanInfo(planEditVo);
            setData("message","修改成功");
        }
    }

    /**
     * 根据查询条件查询数据
     *
     * @param planQueryVo
     */
    @PostMapping("queryPlanInfoList")
    public void queryPlanInfoList(PlanQueryVo planQueryVo) {
        setData("planList", planReadService.queryPlanInfoList(planQueryVo));
    }

    /**
     * 根据查询条件分页查询数据
     *
     * @param planQueryVo
     * @param pageParam
     */
    @PostMapping("queryPlanInfoPage")
    public void queryPlanInfoPage(PlanQueryVo planQueryVo, PageParam pageParam) {
        setPageBean(planReadService.queryPlanInfoPage(planQueryVo, pageParam));
    }

    @PostMapping("queryMRPage")
    public void queryMRPage(MrQueryVo mrQueryVo, PageParam pageParam) {
        setPageBean(planReadService.queryMRPage(mrQueryVo, pageParam));
    }

    @PostMapping("addMR")
    public void addMR() {
        List<MrAddVo> list = new ArrayList<>();
        PlanQueryVo planQueryVo = new PlanQueryVo();
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        planQueryVo.setStartDate(cale.getTime());
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        planQueryVo.setEndDate(cale.getTime());
        planQueryVo.setIsCompute("否");
        List<PlanInfoVo> planList = planReadService.queryPlanInfoList(planQueryVo);
        Map<String, BomTreeNode> map = bomReadService.queryBomTreeList();
        for (int i = 0; i < planList.size(); i++) {
            PlanInfoVo planInfoVo = planList.get(i);
            planList.get(i).setIsCompute("是");
            BomTreeNode bomTreeNode = map.get(planInfoVo.getId());
            List<BomTreeNode> children;
            for (int j = 0; j < bomTreeNode.getChildren().size(); j++) {
                if (bomTreeNode.getChildren().get(j).getChildren().size() == 0) {
                    BomTreeNode bomTreeNode1 = bomTreeNode.getChildren().get(j);
                    MrAddVo mrAddVo = new MrAddVo();
                    mrAddVo.setId(planInfoVo.getId());
                    mrAddVo.setName(planInfoVo.getName());
                    mrAddVo.setNum(planInfoVo.getNum() * bomTreeNode1.getNum());
                    mrAddVo.setUnit(planInfoVo.getUnit());
                    mrAddVo.setSid(bomTreeNode1.getId());
                    mrAddVo.setSname(bomTreeNode1.getName());
                    mrAddVo.setSunit(bomTreeNode1.getUnit());
                    mrAddVo.setIsOrder("否");
                    mrAddVo.setComputeDate(timeService.getSysDate());
                    mrAddVo.setPnum(planInfoVo.getNum());
                    list.add(mrAddVo);
                } else {
                    children = bomTreeNode.getChildren().get(j).getChildren();
                    for (int k = 0; k < children.size(); k++) {
                        MrAddVo mrAddVo = new MrAddVo();
                        mrAddVo.setId(planInfoVo.getId());
                        mrAddVo.setName(planInfoVo.getName());
                        mrAddVo.setNum(planInfoVo.getNum() * bomTreeNode.getChildren().get(j).getNum() * children.get(k).getNum());
                        mrAddVo.setUnit(planInfoVo.getUnit());
                        mrAddVo.setSid(children.get(k).getId());
                        mrAddVo.setSname(children.get(k).getName());
                        mrAddVo.setSunit(children.get(k).getUnit());
                        mrAddVo.setIsOrder("否");
                        mrAddVo.setComputeDate(timeService.getSysDate());
                        mrAddVo.setPnum(planInfoVo.getNum());
                        list.add(mrAddVo);
                    }
                }
            }
        }
        planWriteService.addMrInfo(list, planList);
    }
}
