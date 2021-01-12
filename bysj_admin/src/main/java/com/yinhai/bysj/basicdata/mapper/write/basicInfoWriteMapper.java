package com.yinhai.bysj.basicdata.mapper.write;

import com.yinhai.bysj.basicdata.entity.Wl;
import com.yinhai.bysj.basicdata.vo.WlEditVo;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;

import java.util.List;

/**
* 物料表(Wl)表数据库访问层
*
* @author default
* @since 2020-11-26 09:31:45
*/
public interface basicInfoWriteMapper {
    /**
    * 新增数据
    *
    * @param wl 实例对象
    * @return 影响行数
    */
    int insert(Wl wl);

    /**
    * 修改数据
    *
    * @param wl 实例对象
    * @return 影响行数
    */
    int update(Wl wl);

    /**
    * 通过主键物理删除数据
    *
    * @param id 主键
    * @return 影响行数
    */
    int deleteById(String id);

    /**
    * 通过主键逻辑删除数据
    *
    * @param id 主键
    * @return 影响行数
    */
    int updateToDestroyById(String id);

    int updateRawMaterialById(Wl wl);
}
