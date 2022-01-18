package com.yinhai.bysj.basicdata.service.write;

import com.yinhai.bysj.basicdata.entity.Wl;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import com.yinhai.bysj.basicdata.vo.WlAddVo;
import com.yinhai.bysj.basicdata.vo.WlEditVo;
import java.util.List;

/**
* 物料表(Wl)表服务接口
*
* @author default
* @since 2020-11-26 09:31:48
*/
public interface basicInfoWriteService {
    /**
    * 新增数据
    *
    * @param wlAddVo 实例对象
    * @return 实例对象
    */
    Wl addWlInfo(WlAddVo wlAddVo);


    /**
    * 修改数据
    *
    * @param wlEditVo 实例对象
    * @return 实例对象
    */
    Wl editWlInfo(WlEditVo wlEditVo);

    /**
    * 通过主键物理删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    int removeWlById(String id);


    /**
    * 通过主键逻辑删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    int logicRemoveWlById(String id);

    Wl updateRawMaterialById(WlEditVo wlEditVo);

}
