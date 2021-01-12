package com.yinhai.bysj.basicdata.rest;

import com.yinhai.bysj.basicdata.service.read.basicInfoReadService;
import com.yinhai.bysj.basicdata.service.write.basicInfoWriteService;
import com.yinhai.bysj.basicdata.vo.WlAddVo;
import com.yinhai.bysj.basicdata.vo.WlEditVo;
import com.yinhai.bysj.basicdata.vo.WlQueryVo;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.service.time.TimeService;
import com.yinhai.ta404.core.validate.annotation.V;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
/**
* 物料表(Wl)表控制层
*
* @author default
* @since 2020-11-26 09:31:49
*/
@Validated
@RestService("basicInfo")
public class basicInfoRestService extends BaseRestService{
    /**
    * 服务对象
    */
    @Resource
    private basicInfoReadService basicInfoReadService;
    @Resource
    private basicInfoWriteService basicInfoWriteService;
    @Autowired
    private TimeService timeService;
    /**
    * 新增数据
    *
    * @param wlAddVo
    */
    @PostMapping("addWlInfo")
    public void addWlInfo(@Valid WlAddVo wlAddVo) {
        basicInfoWriteService.addWlInfo(wlAddVo);
    }

    /**
    * 通过主键物理删除数据
    *
    * @param id 主键
    */
    @PostMapping("removeWlById")
    public void removeWlById(@V({"notnull"})String id) {
        basicInfoWriteService.removeWlById(id);
    }

    /**
    * 通过主键逻辑删除数据
    *
    * @param id 主键
    */
    @PostMapping("removeWl")
    public void logicRemoveWl(@V({"notnull"})String id) {
        basicInfoWriteService.logicRemoveWlById(id);
    }

    /**
    * 编辑数据
    *
    * @param wlEditVo
    */
    @PostMapping("editWlInfo")
    public void editWlInfo(@Valid WlEditVo wlEditVo)  {
        basicInfoWriteService.editWlInfo(wlEditVo);
    }

    /**
     *  录入价格
     * @Param: [wlEditVo]
     * @Return: void
     */

    @PostMapping("addRawPrice")
    public void addRawPrice(@Valid WlEditVo wlEditVo){
        wlEditVo.setPricedate(timeService.getSysTimestamp());
        basicInfoWriteService.editWlInfo(wlEditVo);
    }


    /**
    * 根据主键查询数据
    *
    * @param id 主键
    */
    @PostMapping("queryWlInfoById")
        public void queryWlInfoById(@V({"notnull"})String id){
        setData("wlInfo",basicInfoReadService.queryWlInfoById(id));
    }

    /**
    * 根据查询条件查询数据
    *
    * @param wlQueryVo
    */
    @PostMapping("queryWlInfoList")
    public void queryWlInfoList(WlQueryVo wlQueryVo){
        setData("wlList",basicInfoReadService.queryWlInfoList(wlQueryVo));
    }

    /**
    * 根据查询条件分页查询数据
    *
    * @param wlQueryVo
    * @param pageParam
    */
    @PostMapping("queryWlInfoPage")
    public void queryWlInfoPage(WlQueryVo wlQueryVo, PageParam pageParam){
        setPageBean(basicInfoReadService.queryWlInfoPage(wlQueryVo, pageParam));
    }

    @PostMapping("queryMainListPage")
    public void queryMainList(WlQueryVo wlQueryVo, PageParam pageParam){
        setPageBean(basicInfoReadService.queryMainListPage(wlQueryVo, pageParam));
    }

    /**
     *  根据id修改数据
     * @Param: [wlEditVo]
     * @Return: void
     */

    @PostMapping("updateRawMaterialById")
    public void updateRawMaterialById(WlEditVo wlEditVo){
        basicInfoWriteService.updateRawMaterialById(wlEditVo);
    }

    @PostMapping("queryRawListPage")
    public void queryRawListPage(WlQueryVo wlQueryVo,PageParam pageParam){
        setPageBean(basicInfoReadService.queryRawListPage(wlQueryVo, pageParam));
    }
}
