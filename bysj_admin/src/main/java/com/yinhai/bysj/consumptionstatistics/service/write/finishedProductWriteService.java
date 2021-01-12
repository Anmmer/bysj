package com.yinhai.bysj.consumptionstatistics.service.write;

import com.yinhai.bysj.consumptionstatistics.entity.FinishedProduct;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductAddVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductEditVo;
import java.util.List;

/**
* 已生产产成品(FinishedProduct)表服务接口
*
* @author default
* @since 2020-12-25 17:09:57
*/
public interface finishedProductWriteService {
    /**
    * 新增数据
    *
    * @param finishedProductAddVo 实例对象
    * @return 实例对象
    */
    FinishedProduct addFinishedProductInfo(FinishedProductAddVo finishedProductAddVo);

    /**
    * 修改数据
    *
    * @param finishedProductEditVo 实例对象
    * @return 实例对象
    */
    FinishedProduct editFinishedProductInfo(FinishedProductEditVo finishedProductEditVo);

    /**
    * 通过主键物理删除数据
    *
    * @param inde 主键
    * @return 是否成功
    */
    int removeFinishedProductById(Integer inde);


    /**
    * 通过主键逻辑删除数据
    *
    * @param inde 主键
    * @return 是否成功
    */
    int logicRemoveFinishedProductById(Integer inde);

    int batchUpdate(List<FinishedProduct> list);
}
