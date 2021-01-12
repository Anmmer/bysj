package com.yinhai.bysj.purchasemanagement.mapper.read;

import com.yinhai.bysj.purchasemanagement.vo.MrQueryVo;
import com.yinhai.bysj.purchasemanagement.vo.MrInfoVo;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* (Mr)表数据库访问层
*
* @author default
* @since 2020-12-15 11:24:26
*/
public interface MRReadMapper  extends Ta404SupportMapper{



    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<MrInfoVo> queryList(MrQueryVo mrQueryVo);

}
