package com.yinhai.bysj.basicdata.service.write.impl;

import com.yinhai.bysj.basicdata.entity.Bom;
import com.yinhai.bysj.basicdata.mapper.write.BomWriteMapper;
import com.yinhai.bysj.basicdata.service.write.BomWriteService;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.TaTransactional;
import com.yinhai.ta404.core.service.BaseService;
import com.yinhai.ta404.core.utils.UUIDUtils;
import com.yinhai.ta404.core.exception.AppException;
import org.springframework.beans.BeanUtils;
import com.yinhai.bysj.basicdata.vo.BomInfoVo;
import com.yinhai.bysj.basicdata.vo.BomAddVo;
import com.yinhai.bysj.basicdata.vo.BomEditVo;
import javax.annotation.Resource;
import java.util.List;

/**
* BOM资料(Bom)表服务实现类
*
* @author default
* @since 2020-11-27 16:03:40
*/
@Service
@TaTransactional
public class BomWriteServiceImpl extends BaseService implements BomWriteService {
@Resource
private BomWriteMapper bomWriteMapper;
    /**
    * 新增数据
    *
    * @param bomAddVo 实例对象
    * @return 实例对象
    */
    @Override
    public Bom addBomInfo(BomAddVo bomAddVo) {
        Bom bom = new Bom();

        BeanUtils.copyProperties(bomAddVo, bom);

//        bom.setIndex(UUIDUtils.getUUID());

        int i = bomWriteMapper.insert(bom);
        if (i != 1) {
        throw new AppException("未添加任何数据，请重试");
        }
        return bom;
    }

    /**
    * 修改数据
    *
    * @param bomEditVo 实例对象
    * @return 实例对象
    */
    @Override
    public Bom editBomInfo(BomEditVo bomEditVo) {
        Bom bom = new Bom();
        BeanUtils.copyProperties(bomEditVo, bom);
//        bom.setUpdateTime(getSysTimestamp());
        int i = bomWriteMapper.update(bom);
        if (i != 1) {
        throw new AppException("未更新任何数据，请重试");
        }
        return bom;
    }
    /**
    * 通过主键物理删除数据
    *
    * @param
    * @return 受影响行数
    */
    @Override
    public int removeBomBySid(BomInfoVo bomInfoVo) {
        int i = this.bomWriteMapper.deleteBySid(bomInfoVo);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }

    /**
    * 通过主键逻辑删除数据
    *
    * @param index 主键
    * @return 受影响行数
    */
    @Override
    public int logicRemoveBomById(Integer index) {
        int i = this.bomWriteMapper.updateToDestroyById(index);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }

    @Override
    public int insertBomBatch(List<Bom> bomList) {
        return this.bomWriteMapper.insertBomBatch(bomList);
    }
}
