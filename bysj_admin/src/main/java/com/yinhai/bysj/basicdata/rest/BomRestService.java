package com.yinhai.bysj.basicdata.rest;

import com.alibaba.fastjson.JSONObject;
import com.yinhai.bysj.basicdata.common.BomTreeNode;
import com.yinhai.bysj.basicdata.common.BomTreeNodeBuilder;
import com.yinhai.bysj.basicdata.entity.Bom;
import com.yinhai.bysj.basicdata.entity.Wl;
import com.yinhai.bysj.basicdata.service.read.BomReadService;
import com.yinhai.bysj.basicdata.service.write.BomWriteService;
import com.yinhai.bysj.basicdata.service.write.basicInfoWriteService;
import com.yinhai.bysj.basicdata.vo.*;
import com.yinhai.ta404.core.service.time.TimeService;
import com.yinhai.ta404.core.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.validate.annotation.V;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * BOM资料(Bom)表控制层
 *
 * @author default
 * @since 2020-11-27 16:03:40
 */
@Validated
@RestService("bom")
@Slf4j
public class BomRestService extends BaseRestService {
    /**
     * 服务对象
     */
    @Resource
    private BomReadService bomReadService;
    @Resource
    private BomWriteService bomWriteService;
    @Autowired
    private basicInfoWriteService basicInfoWriteService;
    @Autowired
    private TimeService timeService;

    /**
     * 新增数据
     *
     * @param bomAddVo
     */
    @PostMapping("addBomInfo")
    @CacheEvict(value="bomTree",allEntries=true)
    public void addBomInfo(@Valid BomAddVo bomAddVo) {
        bomWriteService.addBomInfo(bomAddVo);
    }

    /**
     * 通过主键物理删除数据
     *
     * @param bomInfoVo 主键
     */
    @PostMapping("removeBomBySid")
    @CacheEvict(value="bomTree",allEntries=true)
    public void removeBomById(@Valid BomInfoVo bomInfoVo) {
        if (ValidateUtil.isEmpty(bomInfoVo.getSid())) {
            WlEditVo wlEditVo = new WlEditVo();
            wlEditVo.setBomdate(null);
            wlEditVo.setId(bomInfoVo.getMid());
            wlEditVo.setIsputbom("否");
            basicInfoWriteService.editWlInfo(wlEditVo);
        }
        bomWriteService.removeBomBySid(bomInfoVo);
    }

    /**
     * 通过主键逻辑删除数据
     *
     * @param index 主键
     */
    @PostMapping("removeBom")
    public void logicRemoveBom(@V({"notnull"}) Integer index) {
        bomWriteService.logicRemoveBomById(index);
    }

    /**
     * 编辑数据
     *
     * @param bomEditVo
     */
    @PostMapping("editBomInfo")
    @CacheEvict(value="bomTree",allEntries=true)
    public void editBomInfo(@Valid BomEditVo bomEditVo) {
        bomWriteService.editBomInfo(bomEditVo);
    }

    /**
     * 根据主键查询数据
     *
     * @param index 主键
     */
    @PostMapping("queryBomInfoById")
    public void queryBomInfoById(@V({"notnull"}) Integer index) {
        setData("bomInfo", bomReadService.queryBomInfoById(index));
    }

    /**
     * 根据查询条件查询数据
     *
     * @param bomQueryVo
     */
    @PostMapping("queryBomInfoList")
    public void queryBomInfoList(BomQueryVo bomQueryVo) {
        setData("bomList", bomReadService.queryBomInfoList(bomQueryVo));
    }

    @PostMapping("insertBomBatch")
    @CacheEvict(value="bomTree",allEntries=true)
    public void insertBomBatch(String addBom, String id) {
        List<Bom> list = JSONObject.parseArray(addBom, Bom.class);
        WlEditVo wlEditVo = new WlEditVo();
        wlEditVo.setId(id);
        wlEditVo.setIsputbom("是");
        wlEditVo.setBomdate(timeService.getSysDate());
        bomWriteService.insertBomBatch(list);
        basicInfoWriteService.editWlInfo(wlEditVo);
    }

    @PostMapping("queryBomTree")
    public void queryBomTree(String id){
        Map<String, BomTreeNode> map= bomReadService.queryBomTreeList();
        setData("bomTree", map.get(id));
    }

    @PostMapping("queryBomList")
    public void queryBomList(){
        setData("bomList",BomTreeNodeBuilder.build(bomReadService.queryBomList()));
    }
}
