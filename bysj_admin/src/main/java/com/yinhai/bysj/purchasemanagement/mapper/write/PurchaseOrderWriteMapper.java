package com.yinhai.bysj.purchasemanagement.mapper.write;

import com.yinhai.bysj.purchasemanagement.entity.PurchaseOrder;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderInfoVo;

import java.util.List;

/**
* 采购订单(PurchaseOrder)表数据库访问层
*
* @author default
* @since 2020-12-21 11:02:22
*/
public interface PurchaseOrderWriteMapper {
    /**
    * 新增数据
    *
    * @param purchaseOrderList 实例对象
    * @return 影响行数
    */
    int insert(List<PurchaseOrderInfoVo> purchaseOrderList);

    /**
    * 修改数据
    *
    * @param purchaseOrder 实例对象
    * @return 影响行数
    */
    int update(PurchaseOrder purchaseOrder);

    int batchUpdate(List<PurchaseOrder> purchaseOrderList);

}
