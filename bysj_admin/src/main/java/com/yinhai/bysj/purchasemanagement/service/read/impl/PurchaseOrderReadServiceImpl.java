package com.yinhai.bysj.purchasemanagement.service.read.impl;

import com.yinhai.bysj.purchasemanagement.mapper.read.PurchaseOrderReadMapper;
import com.yinhai.bysj.purchasemanagement.service.read.PurchaseOrderReadService;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderQueryVo;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;

import javax.annotation.Resource;
import java.util.List;

/**
* 采购订单(PurchaseOrder)表服务实现类
*
* @author default
* @since 2020-12-21 11:02:22
*/
@Service
@NoTransactional
public class PurchaseOrderReadServiceImpl implements PurchaseOrderReadService {
@Resource
private PurchaseOrderReadMapper purchaseOrderReadMapper;


    /**
    * 根据查询条件查询数据
    *
    * @param purchaseOrderQueryVo
    */
    @Override
    public List<PurchaseOrderInfoVo> queryPurchaseOrderInfoList(PurchaseOrderQueryVo purchaseOrderQueryVo) {
        return purchaseOrderReadMapper.queryList(purchaseOrderQueryVo);
    }

    /**
    * 根据查询条件分页查询数据
    *
    * @param purchaseOrderQueryVo
    * @param pageParam
    */
    @Override
    public Page<PurchaseOrderInfoVo> queryPurchaseOrderInfoPage(PurchaseOrderQueryVo purchaseOrderQueryVo, PageParam pageParam) {

        purchaseOrderReadMapper.beginPager(pageParam);
        return purchaseOrderReadMapper.endPager(purchaseOrderReadMapper.queryList(purchaseOrderQueryVo));
    }

}
