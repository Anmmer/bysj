package com.yinhai.bysj.basicdata.service.write.impl;

import com.yinhai.bysj.basicdata.entity.Wl;
import com.yinhai.bysj.basicdata.mapper.read.basicInfoReadMapper;
import com.yinhai.bysj.basicdata.mapper.write.basicInfoWriteMapper;
import com.yinhai.bysj.basicdata.service.write.basicInfoWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.TaTransactional;
import com.yinhai.ta404.core.service.BaseService;
import com.yinhai.ta404.core.utils.UUIDUtils;
import com.yinhai.ta404.core.exception.AppException;
import org.springframework.beans.BeanUtils;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import com.yinhai.bysj.basicdata.vo.WlAddVo;
import com.yinhai.bysj.basicdata.vo.WlEditVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物料表(Wl)表服务实现类
 *
 * @author default
 * @since 2020-11-26 09:31:49
 */
@Service
@TaTransactional
public class basicInfoWriteServiceImpl extends BaseService implements basicInfoWriteService {
    @Resource
    private basicInfoWriteMapper basicInfoWriteMapper;
    @Autowired
    private basicInfoReadMapper basicInfoReadMapper;

    /**
     * 新增数据
     *
     * @param wlAddVo 实例对象
     * @return 实例对象
     */
    @Override
    public Wl addWlInfo(WlAddVo wlAddVo) {
        Wl wl = new Wl();
        Integer num = Integer.valueOf(this.basicInfoReadMapper.queryLast().getId())+1;
        BeanUtils.copyProperties(wlAddVo, wl);
        wl.setId(String.valueOf(num));
        int i = basicInfoWriteMapper.insert(wl);
        if (i != 1) {
            throw new AppException("未添加任何数据，请重试");
        }
        return wl;
    }


    /**
     * 修改数据
     *
     * @param wlEditVo 实例对象
     * @return 实例对象
     */
    @Override
    public Wl editWlInfo(WlEditVo wlEditVo) {
        Wl wl = new Wl();
        BeanUtils.copyProperties(wlEditVo, wl);
//        wl.setBomdate(getSysTimestamp());
        int i = basicInfoWriteMapper.update(wl);
        if (i != 1) {
            throw new AppException("未更新任何数据，请重试");
        }
        return wl;
    }

    /**
     * 通过主键物理删除数据
     *
     * @param id 主键
     * @return 受影响行数
     */
    @Override
    public int removeWlById(String id) {
        int i = this.basicInfoWriteMapper.deleteById(id);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }

    /**
     * 通过主键逻辑删除数据
     *
     * @param id 主键
     * @return 受影响行数
     */
    @Override
    public int logicRemoveWlById(String id) {
        int i = this.basicInfoWriteMapper.updateToDestroyById(id);
        if (i == 0) throw new AppException("未删除任何数据，请重试");
        return i;
    }

    @Override
    public Wl updateRawMaterialById(WlEditVo wlEditVo) {
        Wl wl = new Wl();
        BeanUtils.copyProperties(wlEditVo, wl);
//        wl.setBomdate(getSysTimestamp());
        int i = basicInfoWriteMapper.updateRawMaterialById(wl);
        if (i != 1) {
            throw new AppException("未更新任何数据，请重试");
        }
        return wl;
    }
}
