package com.yinhai.bysj.purchasemanagement.service.read;

import com.yinhai.bysj.purchasemanagement.entity.PurchaseOrder;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderQueryVo;
import java.util.List;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;


/**
* 采购订单(PurchaseOrder)表服务接口
*
* @author default
* @since 2020-12-21 11:02:22
*/
public interface PurchaseOrderReadService {


    /**
    * 根据查询条件查询数据
    *
    * @param purchaseOrderQueryVo
    */
    List<PurchaseOrderInfoVo> queryPurchaseOrderInfoList(PurchaseOrderQueryVo purchaseOrderQueryVo);

    /**
    * 根据查询条件分页查询数据
    *
    * @param purchaseOrderQueryVo
    * @param pageParam
    */
    Page<PurchaseOrderInfoVo> queryPurchaseOrderInfoPage(PurchaseOrderQueryVo purchaseOrderQueryVo, PageParam pageParam);


}
