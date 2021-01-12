package com.yinhai.bysj.purchasemanagement.mapper.write;

import com.yinhai.bysj.purchasemanagement.entity.Mr;
import com.yinhai.bysj.purchasemanagement.vo.MrAddVo;

import java.util.List;

/**
* (Mr)表数据库访问层
*
* @author default
* @since 2020-12-15 11:24:26
*/
public interface MRWriteMapper {
    /**
    * 新增数据
    *
    * @param mrAddVo 实例对象
    * @return 影响行数
    */
    int insert(List<MrAddVo> mrAddVo);

    int batchUpdate(List<MrAddVo> mrAddVos);

}
