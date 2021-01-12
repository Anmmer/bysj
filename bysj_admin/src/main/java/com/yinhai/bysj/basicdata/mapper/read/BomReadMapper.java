package com.yinhai.bysj.basicdata.mapper.read;

import com.yinhai.bysj.basicdata.entity.Bom;
import com.yinhai.bysj.basicdata.vo.BomQueryVo;
import com.yinhai.bysj.basicdata.vo.BomInfoVo;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import org.apache.ibatis.annotations.Param;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* BOM资料(Bom)表数据库访问层
*
* @author default
* @since 2020-11-27 16:03:40
*/
public interface BomReadMapper  extends Ta404SupportMapper{

    /**
    * 通过ID查询单条数据
    *
    * @param index 主键
    * @return 实例对象
    */
    BomInfoVo queryById(Integer index);

    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<WlInfoVo> queryList(BomQueryVo bomQueryVo);

    List<WlInfoVo> queryBomList();


}
