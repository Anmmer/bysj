package com.yinhai.bysj.basicdata.mapper.read;

import com.yinhai.bysj.basicdata.entity.Wl;
import com.yinhai.bysj.basicdata.vo.WlQueryVo;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import org.apache.ibatis.annotations.Param;
import com.yinhai.ta404.module.mybatis.mapper.Ta404SupportMapper;
import java.util.List;

/**
* 物料表(Wl)表数据库访问层
*
* @author default
* @since 2020-11-26 09:31:45
*/
public interface basicInfoReadMapper  extends Ta404SupportMapper{

    /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
    WlInfoVo queryById(String id);

    /**
    * 查询指定行数据
    *
    * @return 对象列表
    */
    List<WlInfoVo> queryList(WlQueryVo wlQueryVo);

    WlInfoVo queryLast();

    List<WlInfoVo> queryMainList(WlQueryVo wlQueryVo);

    List<WlInfoVo> queryRawList(WlQueryVo wlQueryVo);

}
