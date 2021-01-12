package com.yinhai.bysj.purchasemanagement.service.read.impl;

import com.yinhai.bysj.purchasemanagement.mapper.read.purchaseManagementReadMapper;
import com.yinhai.bysj.purchasemanagement.service.read.purchaseManagementReadService;
import com.yinhai.bysj.purchasemanagement.vo.GysQueryVo;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 供应商(Gys)表服务实现类
 *
 * @author default
 * @since 2020-11-26 14:16:11
 */
@Service
@NoTransactional
public class purchaseManagementReadServiceImpl implements purchaseManagementReadService {
    @Resource
    private purchaseManagementReadMapper purchaseManagementReadMapper;


    /**
     * 根据查询条件查询数据
     *
     * @param
     * @return
     */
    @Override
    public List<GysQueryVo> queryGysInfoList() {
        return purchaseManagementReadMapper.queryList();
    }


}
