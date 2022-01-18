package com.yinhai.bysj.consumptionstatistics.rest;

import com.yinhai.bysj.consumptionstatistics.entity.FinishedProduct;
import com.yinhai.bysj.consumptionstatistics.service.read.finishedProductReadService;
import com.yinhai.bysj.consumptionstatistics.service.write.finishedProductWriteService;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductAddVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductEditVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductQueryVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PlanQueryVo;
import org.springframework.web.bind.annotation.*;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.validate.annotation.V;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 已生产产成品(FinishedProduct)表控制层
 *
 * @author default
 * @since 2020-12-25 17:09:58
 */
@Validated
@RestService("finishedProduct")
public class finishedProductRestService extends BaseRestService {
    /**
     * 服务对象
     */
    @Resource
    private finishedProductReadService finishedProductReadService;
    @Resource
    private finishedProductWriteService finishedProductWriteService;

    /**
     * 新增数据
     *
     * @param planQueryVo
     */
    @PostMapping("addFinishedProductInfo")
    public void addFinishedProductInfo(@Valid PlanQueryVo planQueryVo) {
        PlanInfoVo planInfoVo = finishedProductReadService.querySunNumById(planQueryVo);
        if (planInfoVo.getConsumeNum() == null) {
            planInfoVo.setConsumeNum(0);
        }
        if (planInfoVo.getNum() < planQueryVo.getNum() + planInfoVo.getConsumeNum()) {
            setData("message", 1);
        } else {
            FinishedProductAddVo finishedProductAddVo = new FinishedProductAddVo();
            finishedProductAddVo.setId(planQueryVo.getId());
            finishedProductAddVo.setNum(planQueryVo.getNum());
            finishedProductAddVo.setIsCompute(planQueryVo.getIsCompute());
            finishedProductWriteService.addFinishedProductInfo(finishedProductAddVo);
            setData("message", 2);
        }
    }

    /**
     * 通过主键物理删除数据
     *
     * @param inde 主键
     */
    @PostMapping("removeFinishedProductById")
    public void removeFinishedProductById(@V({"notnull"}) Integer inde) {
        finishedProductWriteService.removeFinishedProductById(inde);
    }

    /**
     * 通过主键逻辑删除数据
     *
     * @param inde 主键
     */
    @PostMapping("removeFinishedProduct")
    public void logicRemoveFinishedProduct(@V({"notnull"}) Integer inde) {
        finishedProductWriteService.logicRemoveFinishedProductById(inde);
    }

    /**
     * 编辑数据
     *
     * @param planQueryVo
     */
    @PostMapping("editFinishedProductInfo")
    public void editFinishedProductInfo(@Valid PlanQueryVo planQueryVo) {
        PlanInfoVo planInfoVo = finishedProductReadService.querySunNumById(planQueryVo);
        FinishedProductInfoVo finishedProduct = finishedProductReadService.queryFinishedProductInfoByInde(planQueryVo.getInde());
        if (planInfoVo.getConsumeNum() == null) {
            planInfoVo.setConsumeNum(0);
        }
        if (("是").equals(finishedProductReadService.queryFinishedProductInfoByInde(planQueryVo.getInde()).getIsCompute())) {
            setData("message", 2);
        } else if (planInfoVo.getNum() < planQueryVo.getNum()+planInfoVo.getConsumeNum()-finishedProduct.getNum()) {
            setData("message", 1);
        } else {
            FinishedProductEditVo finishedProductEditVo = new FinishedProductEditVo();
            finishedProductEditVo.setId(planInfoVo.getId());
            finishedProductEditVo.setInde(planQueryVo.getInde());
            finishedProductEditVo.setNum(planQueryVo.getNum());
            finishedProductWriteService.editFinishedProductInfo(finishedProductEditVo);
            setData("message", 3);
        }
    }

    /**
     * 根据主键查询数据
     *
     * @param inde 主键
     */
    @PostMapping("queryFinishedProductInfoById")
    public void queryFinishedProductInfoById(@V({"notnull"}) Integer inde) {
        setData("finishedProductInfo", finishedProductReadService.queryFinishedProductInfoByInde(inde));
    }

    /**
     * 根据查询条件查询数据
     *
     * @param finishedProductQueryVo
     */
    @PostMapping("queryFinishedProductInfoList")
    public void queryFinishedProductInfoList(FinishedProductQueryVo finishedProductQueryVo) {
        setData("finishedProductList", finishedProductReadService.queryFinishedProductInfoList(finishedProductQueryVo));
    }

    /**
     * 根据查询条件分页查询数据
     *
     * @param finishedProductQueryVo
     * @param pageParam
     */
    @PostMapping("queryFinishedProductInfoPage")
    public void queryFinishedProductInfoPage(FinishedProductQueryVo finishedProductQueryVo, PageParam pageParam) {
        setPageBean(finishedProductReadService.queryFinishedProductInfoPage(finishedProductQueryVo, pageParam));
    }

    @PostMapping("queryNumListPage")
    public void queryNumListPage(PlanQueryVo planQueryVo, PageParam pageParam) {
        setPageBean(finishedProductReadService.queryNumListPage(planQueryVo, pageParam));
    }

}
