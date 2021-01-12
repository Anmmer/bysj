package com.yinhai.bysj.basicdata.service.read;

import com.yinhai.bysj.basicdata.common.BomTreeNode;
import com.yinhai.bysj.basicdata.vo.BomInfoVo;
import com.yinhai.bysj.basicdata.vo.BomQueryVo;
import java.util.List;
import java.util.Map;

import com.yinhai.bysj.basicdata.vo.WlInfoVo;


/**
* BOM资料(Bom)表服务接口
*
* @author default
* @since 2020-11-27 16:03:40
*/
public interface BomReadService {

    /**
    * 通过ID查询单条数据
    *
    * @param index 主键
    * @return 实例对象
    */
    BomInfoVo queryBomInfoById(Integer index);

    /**
    * 根据查询条件查询数据
    *
    * @param bomQueryVo
    */
    List<WlInfoVo> queryBomInfoList(BomQueryVo bomQueryVo);


    Map<String, BomTreeNode> queryBomTreeList();

    List<WlInfoVo> queryBomList();

}
