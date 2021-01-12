package com.yinhai.bysj.purchasemanagement.service.read;

import com.yinhai.bysj.purchasemanagement.vo.GysQueryVo;
import java.util.List;


/**
* 供应商(Gys)表服务接口
*
* @author default
* @since 2020-11-26 14:16:10
*/
public interface purchaseManagementReadService {

    /**
    * 根据查询条件查询数据
    *
     * @param
     * @return
     */
    List<GysQueryVo> queryGysInfoList();


}
