package com.yinhai.bysj.consumptionstatistics.rest;

import com.yinhai.bysj.consumptionstatistics.service.read.finishedProductReadService;
import com.yinhai.bysj.consumptionstatistics.service.write.finishedProductWriteService;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductAddVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductEditVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductQueryVo;
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
     * @param finishedProductAddVo
     */
    @PostMapping("addFinishedProductInfo")
    public void addFinishedProductInfo(@Valid FinishedProductAddVo finishedProductAddVo) {
        finishedProductWriteService.addFinishedProductInfo(finishedProductAddVo);
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
     * @param finishedProductEditVo
     */
    @PostMapping("editFinishedProductInfo")
    public void editFinishedProductInfo(@Valid FinishedProductEditVo finishedProductEditVo) {
        if(finishedProductReadService.queryNumById(finishedProductEditVo.getId()).getNum() < finishedProductEditVo.getNum()){
            setData("message",1);
        }else if (finishedProductReadService.queryFinishedProductInfoByInde(finishedProductEditVo.getInde()).getIsCompute() == "是") {
            setData("message", 2);
        } else {
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
