package com.yinhai.bysj.purchasemanagement.service.write;

import com.yinhai.bysj.purchasemanagement.entity.PurchaseOrder;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderEditVo;
import java.util.List;

/**
* 采购订单(PurchaseOrder)表服务接口
*
* @author default
* @since 2020-12-21 11:02:22
*/
public interface PurchaseOrderWriteService {
    /**
    * 新增数据
    *
    * @return 实例对象
    */
    int addPurchaseOrderInfo();

    /**
    * 修改数据
    *
    * @param purchaseOrderEditVo 实例对象
    * @return 实例对象
    */
    PurchaseOrder editPurchaseOrderInfo(PurchaseOrderEditVo purchaseOrderEditVo);

    void batchOrderArrival(List<PurchaseOrderEditVo> purchaseOrderList);

}
