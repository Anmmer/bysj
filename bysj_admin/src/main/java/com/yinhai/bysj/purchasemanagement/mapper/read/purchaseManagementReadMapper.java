package com.yinhai.bysj.purchasemanagement.mapper.read;

import com.yinhai.bysj.purchasemanagement.vo.GysQueryVo;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* 供应商(Gys)表数据库访问层
*
* @author default
* @since 2020-11-26 14:16:10
*/
public interface purchaseManagementReadMapper  extends Ta404SupportMapper{


    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<GysQueryVo> queryList();

}
