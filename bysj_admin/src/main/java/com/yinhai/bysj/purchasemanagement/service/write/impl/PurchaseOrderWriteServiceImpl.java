package com.yinhai.bysj.purchasemanagement.service.write.impl;

import com.yinhai.bysj.basicdata.common.Constant;
import com.yinhai.bysj.purchasemanagement.entity.PurchaseOrder;
import com.yinhai.bysj.purchasemanagement.mapper.read.PurchaseOrderReadMapper;
import com.yinhai.bysj.purchasemanagement.mapper.write.MRWriteMapper;
import com.yinhai.bysj.purchasemanagement.mapper.write.PurchaseOrderWriteMapper;
import com.yinhai.bysj.purchasemanagement.service.write.PurchaseOrderWriteService;
import com.yinhai.bysj.purchasemanagement.vo.MrAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.TaTransactional;
import com.yinhai.ta404.core.service.BaseService;
import com.yinhai.ta404.core.exception.AppException;
import org.springframework.beans.BeanUtils;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderInfoVo;
import com.yinhai.bysj.purchasemanagement.vo.PurchaseOrderEditVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 采购订单(PurchaseOrder)表服务实现类
 *
 * @author default
 * @since 2020-12-21 11:02:22
 */
@Service
@TaTransactional
public class PurchaseOrderWriteServiceImpl extends BaseService implements PurchaseOrderWriteService {
    @Resource
    private PurchaseOrderWriteMapper purchaseOrderWriteMapper;
    @Autowired
    private PurchaseOrderReadMapper purchaseOrderReadMapper;
    @Autowired
    private MRWriteMapper mrWriteMapper;

    /**
     * 新增数据
     *
     * @return 实例对象
     */
    @Override
    public int addPurchaseOrderInfo() {
        List<PurchaseOrderInfoVo> purchaseOrderInfoList = purchaseOrderReadMapper.queryMrNUMList();
        List<MrAddVo> mrList = new ArrayList<>();
        String orderId = Constant.getOrderIdByTime();
        for(int i = 0;i<purchaseOrderInfoList.size();i++){
            purchaseOrderInfoList.get(i).setOrderId(orderId);
            purchaseOrderInfoList.get(i).setInputDate(getSysDate());
            purchaseOrderInfoList.get(i).setIsDelivered("否");
            MrAddVo mr = new MrAddVo();
            mr.setSid(purchaseOrderInfoList.get(i).getId());
            mr.setIsOrder("是");
            mrList.add(mr);
        }
        int i = purchaseOrderWriteMapper.insert(purchaseOrderInfoList);
        mrWriteMapper.batchUpdate(mrList);
        if (i == 0) {
            throw new AppException("未添加任何数据，请重试");
        }
        return i;
    }

    /**
     * 修改数据
     *
     * @param purchaseOrderEditVo 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseOrder editPurchaseOrderInfo(PurchaseOrderEditVo purchaseOrderEditVo) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        BeanUtils.copyProperties(purchaseOrderEditVo, purchaseOrder);
        purchaseOrder.setIsDelivered("是");
        purchaseOrder.setDeliveredDate(getSysDate());
        int i = purchaseOrderWriteMapper.update(purchaseOrder);
        if (i != 1) {
            throw new AppException("未更新任何数据，请重试");
        }
        return purchaseOrder;
    }

    @Override
    public void batchOrderArrival(List<PurchaseOrderEditVo> purchaseOrderEditVoList) {
        List<PurchaseOrder> list = new ArrayList<>();
        for(int i = 0;i<purchaseOrderEditVoList.size();i++){
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setOrderId(purchaseOrderEditVoList.get(i).getOrderId());
            purchaseOrder.setId(purchaseOrderEditVoList.get(i).getId());
            purchaseOrder.setIsDelivered("是");
            purchaseOrder.setDeliveredDate(getSysDate());
            list.add(purchaseOrder);
        }
        purchaseOrderWriteMapper.batchUpdate(list);
    }
}
