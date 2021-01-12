package com.yinhai.bysj.purchasemanagement.mapper.read;

import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderQueryVo;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderInfoVo;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* 采购订单(PurchaseOrder)表数据库访问层
*
* @author default
* @since 2020-12-21 11:02:22
*/
public interface PurchaseOrderReadMapper  extends Ta404SupportMapper{


    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<PurchaseOrderInfoVo> queryList(PurchaseOrderQueryVo purchaseOrderQueryVo);

    List<PurchaseOrderInfoVo> queryMrNUMList();

}
