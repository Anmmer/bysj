package com.yinhai.bysj.basicdata.service.write;

import com.yinhai.bysj.basicdata.entity.Bom;
import com.yinhai.bysj.basicdata.vo.BomInfoVo;
import com.yinhai.bysj.basicdata.vo.BomAddVo;
import com.yinhai.bysj.basicdata.vo.BomEditVo;
import java.util.List;

/**
* BOM资料(Bom)表服务接口
*
* @author default
* @since 2020-11-27 16:03:40
*/
public interface BomWriteService {
    /**
    * 新增数据
    *
    * @param bomAddVo 实例对象
    * @return 实例对象
    */
    Bom addBomInfo(BomAddVo bomAddVo);

    /**
    * 修改数据
    *
    * @param bomEditVo 实例对象
    * @return 实例对象
    */
    Bom editBomInfo(BomEditVo bomEditVo);

    /**
    * 通过主键物理删除数据
    *
    * @param bomInfoVo 主键
    * @return 是否成功
    */
    int removeBomBySid(BomInfoVo bomInfoVo);


    /**
    * 通过主键逻辑删除数据
    *
    * @param index 主键
    * @return 是否成功
    */
    int logicRemoveBomById(Integer index);

    int insertBomBatch(List<Bom> bomList);


}
