package com.yinhai.bysj.purchasemanagement.rest;

import com.alibaba.fastjson.JSONObject;
import com.yinhai.bysj.purchasemanagement.service.read.PurchaseOrderReadService;
import com.yinhai.bysj.purchasemanagement.service.write.PurchaseOrderWriteService;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderEditVo;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderQueryVo;
import org.springframework.web.bind.annotation.*;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采购订单(PurchaseOrder)表控制层
 *
 * @author default
 * @since 2020-12-21 11:02:22
 */
@Validated
@RestService("purchaseOrder")
public class PurchaseOrderRestService extends BaseRestService {
    /**
     * 服务对象
     */
    @Resource
    private PurchaseOrderReadService purchaseOrderReadService;
    @Resource
    private PurchaseOrderWriteService purchaseOrderWriteService;

    /**
     * 新增数据
     */
    @PostMapping("addPurchaseOrderInfo")
    public void addPurchaseOrderInfo() {
        purchaseOrderWriteService.addPurchaseOrderInfo();
    }


    /**
     * 根据查询条件分页查询数据
     *
     * @param purchaseOrderQueryVo
     * @param pageParam
     */
    @PostMapping("queryPurchaseOrderInfoPage")
    public void queryPurchaseOrderInfoPage(PurchaseOrderQueryVo purchaseOrderQueryVo, PageParam pageParam) {
        setPageBean(purchaseOrderReadService.queryPurchaseOrderInfoPage(purchaseOrderQueryVo, pageParam));
    }

    @PostMapping("editPurchaseOrderInfo")
    public void editPurchaseOrderInfo(PurchaseOrderEditVo purchaseOrderEditVo){
        purchaseOrderWriteService.editPurchaseOrderInfo(purchaseOrderEditVo);
    }

    @PostMapping("batchOrderArrival")
    public void batchOrderArrival(String list){
        List<PurchaseOrderEditVo> purchaseOrderEditVos = JSONObject.parseArray(list, PurchaseOrderEditVo.class);
        purchaseOrderWriteService.batchOrderArrival(purchaseOrderEditVos);
    }
}
