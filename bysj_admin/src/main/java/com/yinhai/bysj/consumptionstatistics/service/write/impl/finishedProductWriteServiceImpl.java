package com.yinhai.bysj.consumptionstatistics.service.write.impl;

import com.yinhai.bysj.consumptionstatistics.entity.FinishedProduct;
import com.yinhai.bysj.consumptionstatistics.mapper.write.finishedProductWriteMapper;
import com.yinhai.bysj.consumptionstatistics.service.write.finishedProductWriteService;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.TaTransactional;
import com.yinhai.ta404.core.service.BaseService;
import com.yinhai.ta404.core.utils.UUIDUtils;
import com.yinhai.ta404.core.exception.AppException;
import org.springframework.beans.BeanUtils;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductAddVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductEditVo;
import javax.annotation.Resource;
import java.util.List;

/**
* 已生产产成品(FinishedProduct)表服务实现类
*
* @author default
* @since 2020-12-25 17:09:57
*/
@Service
@TaTransactional
public class finishedProductWriteServiceImpl extends BaseService implements finishedProductWriteService {
@Resource
private finishedProductWriteMapper finishedProductWriteMapper;
    /**
    * 新增数据
    *
    * @param finishedProductAddVo 实例对象
    * @return 实例对象
    */
    @Override
    public FinishedProduct addFinishedProductInfo(FinishedProductAddVo finishedProductAddVo) {
        FinishedProduct finishedProduct = new FinishedProduct();

        BeanUtils.copyProperties(finishedProductAddVo, finishedProduct);

        finishedProduct.setInputDate(getSysDate());

        int i = finishedProductWriteMapper.insert(finishedProduct);
        if (i != 1) {
        throw new AppException("未添加任何数据，请重试");
        }
        return finishedProduct;
    }

    /**
    * 修改数据
    *
    * @param finishedProductEditVo 实例对象
    * @return 实例对象
    */
    @Override
    public FinishedProduct editFinishedProductInfo(FinishedProductEditVo finishedProductEditVo) {
        FinishedProduct finishedProduct = new FinishedProduct();
        BeanUtils.copyProperties(finishedProductEditVo, finishedProduct);
        int i = finishedProductWriteMapper.update(finishedProduct);
        if (i != 1) {
        throw new AppException("未更新任何数据，请重试");
        }
        return finishedProduct;
    }
    /**
    * 通过主键物理删除数据
    *
    * @param inde 主键
    * @return 受影响行数
    */
    @Override
    public int removeFinishedProductById(Integer inde) {
        int i = this.finishedProductWriteMapper.deleteById(inde);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }

    /**
    * 通过主键逻辑删除数据
    *
    * @param inde 主键
    * @return 受影响行数
    */
    @Override
    public int logicRemoveFinishedProductById(Integer inde) {
        int i = this.finishedProductWriteMapper.updateToDestroyById(inde);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }

    @Override
    public int batchUpdate(List<FinishedProduct> list) {
        return finishedProductWriteMapper.batchUpdate(list);
    }
}
