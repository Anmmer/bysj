package com.yinhai.bysj.consumptionstatistics.mapper.write;

import com.yinhai.bysj.consumptionstatistics.entity.FinishedProduct;
import java.util.List;

/**
* 已生产产成品(FinishedProduct)表数据库访问层
*
* @author default
* @since 2020-12-25 17:09:56
*/
public interface finishedProductWriteMapper {
    /**
    * 新增数据
    *
    * @param finishedProduct 实例对象
    * @return 影响行数
    */
    int insert(FinishedProduct finishedProduct);

    /**
    * 修改数据
    *
    * @param finishedProduct 实例对象
    * @return 影响行数
    */
    int update(FinishedProduct finishedProduct);

    /**
    * 通过主键物理删除数据
    *
    * @param inde 主键
    * @return 影响行数
    */
    int deleteById(Integer inde);

    /**
    * 通过主键逻辑删除数据
    *
    * @param inde 主键
    * @return 影响行数
    */
    int updateToDestroyById(Integer inde);

    int batchUpdate(List<FinishedProduct> list);
}
