package com.yinhai.bysj.purchasemanagement.rest;

import com.yinhai.bysj.purchasemanagement.service.read.purchaseManagementReadService;
import com.yinhai.bysj.purchasemanagement.vo.GysQueryVo;
import org.springframework.web.bind.annotation.*;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import org.springframework.validation.annotation.Validated;
import javax.annotation.Resource;
/**
* 供应商(Gys)表控制层
*
* @author changan
* @since 2020-11-26 14:17:27
*/
@Validated
@RestService("purchaseManagement")
public class purchaseManagementRestService extends BaseRestService{
    /**
    * 服务对象
    */
    @Resource
    private purchaseManagementReadService purchaseManagementReadService;



    /**
    * 根据查询条件查询数据
    *
    * @param
    */
    @PostMapping("queryGysInfoList")
    public void queryGysInfoList(){
        setData("gysList",purchaseManagementReadService.queryGysInfoList());
    }


}
