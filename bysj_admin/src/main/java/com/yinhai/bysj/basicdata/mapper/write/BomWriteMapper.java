package com.yinhai.bysj.basicdata.mapper.write;

import com.yinhai.bysj.basicdata.entity.Bom;
import com.yinhai.bysj.basicdata.vo.BomInfoVo;

import java.util.List;

/**
* BOM资料(Bom)表数据库访问层
*
* @author default
* @since 2020-11-27 16:03:40
*/
public interface BomWriteMapper {
    /**
    * 新增数据
    *
    * @param bom 实例对象
    * @return 影响行数
    */
    int insert(Bom bom);

    /**
    * 修改数据
    *
    * @param bom 实例对象
    * @return 影响行数
    */
    int update(Bom bom);

    /**
    * 通过主键物理删除数据
    *
    * @param bomInfoVo 主键
    * @return 影响行数
    */
    int deleteBySid(BomInfoVo bomInfoVo);

    /**
    * 通过主键逻辑删除数据
    *
    * @param index 主键
    * @return 影响行数
    */
    int updateToDestroyById(Integer index);

    int insertBomBatch(List<Bom> bomList);

}
