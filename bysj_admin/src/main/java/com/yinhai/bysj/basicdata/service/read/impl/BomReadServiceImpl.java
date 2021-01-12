package com.yinhai.bysj.basicdata.service.read.impl;

import com.yinhai.bysj.basicdata.common.BomTreeNode;
import com.yinhai.bysj.basicdata.common.BomTreeNodeBuilder;
import com.yinhai.bysj.basicdata.mapper.read.BomReadMapper;
import com.yinhai.bysj.basicdata.service.read.BomReadService;
import com.yinhai.bysj.basicdata.vo.BomInfoVo;
import com.yinhai.bysj.basicdata.vo.BomQueryVo;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* BOM资料(Bom)表服务实现类
*
* @author default
* @since 2020-11-27 16:03:40
*/
@Service
@NoTransactional
public class BomReadServiceImpl implements BomReadService {
@Resource
private BomReadMapper bomReadMapper;

    /**
    * 通过ID查询单条数据
    *
    * @param index 主键
    * @return 实例对象
    */
    @Override
    public BomInfoVo queryBomInfoById(Integer index) {
    return this.bomReadMapper.queryById(index);
    }

    /**
    * 根据查询条件查询数据
    *
    * @param bomQueryVo
    */
    @Override
    public List<WlInfoVo> queryBomInfoList(BomQueryVo bomQueryVo) {
        return bomReadMapper.queryList(bomQueryVo);
    }

    @Override
    @Cacheable(value = "bomTree",key = "'bomTree'")
    public Map<String, BomTreeNode> queryBomTreeList() {
        return BomTreeNodeBuilder.build(bomReadMapper.queryBomList());
    }

    @Override
    public List<WlInfoVo> queryBomList() {
        return bomReadMapper.queryBomList();
    }


}
