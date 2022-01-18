package com.yinhai.bysj.basicdata.service.read.impl;

import com.yinhai.bysj.basicdata.common.ObjectUtil;
import com.yinhai.bysj.basicdata.common.ReadExcel;
import com.yinhai.bysj.basicdata.mapper.read.basicInfoReadMapper;
import com.yinhai.bysj.basicdata.service.read.basicInfoReadService;
import com.yinhai.bysj.basicdata.vo.WlAddVo;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import com.yinhai.bysj.basicdata.vo.WlQueryVo;
import com.yinhai.bysj.purchasemanagement.mapper.read.purchaseManagementReadMapper;
import com.yinhai.bysj.purchasemanagement.vo.GysQueryVo;
import com.yinhai.ta404.module.dict.entity.TaDictPo;
import com.yinhai.ta404.module.dict.util.DictUtils;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;

import javax.annotation.Resource;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 物料表(Wl)表服务实现类
 *
 * @author default
 * @since 2020-11-26 09:31:48
 */
@Service
@NoTransactional
public class basicInfoReadServiceImpl implements basicInfoReadService {
    @Resource
    private basicInfoReadMapper basicInfoReadMapper;

//    @Resource
//    private purchaseManagementReadMapper purchaseManagementReadMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WlInfoVo queryWlInfoById(String id) {
        return this.basicInfoReadMapper.queryById(id);
    }

    /**
     * 根据查询条件查询数据
     *
     * @param wlQueryVo
     */
    @Override
    public List<WlInfoVo> queryWlInfoList(WlQueryVo wlQueryVo) {
        return basicInfoReadMapper.queryList(wlQueryVo);
    }

    /**
     * 根据查询条件分页查询数据
     *
     * @param wlQueryVo
     * @param pageParam
     */
    @Override
    public Page<WlInfoVo> queryWlInfoPage(WlQueryVo wlQueryVo, PageParam pageParam) {

        basicInfoReadMapper.beginPager(pageParam);
        return basicInfoReadMapper.endPager(basicInfoReadMapper.queryList(wlQueryVo));
    }

    @Override
    public Page<WlInfoVo> queryMainListPage(WlQueryVo wlQueryVo, PageParam pageParam) {
        basicInfoReadMapper.beginPager(pageParam);
        return basicInfoReadMapper.endPager(basicInfoReadMapper.queryMainList(wlQueryVo));
    }

    @Override
    public Page<WlInfoVo> queryRawListPage(WlQueryVo wlQueryVo, PageParam pageParam) {
        basicInfoReadMapper.beginPager(pageParam);
        return basicInfoReadMapper.endPager(basicInfoReadMapper.queryRawList(wlQueryVo));
    }

//    @Override
//    public Map checkImport(InputStream inputStream) throws Exception {
//        Map resmap = new HashMap();
//        ReadExcel readExcel = new ReadExcel();
//        List<WlAddVo> list = new ArrayList<>();
//        resmap.put("message", "OK");
//        List<Map> listFromExcel = readExcel.getListFromExcel("name,standard,type,unit,gys", inputStream);
//        if (ObjectUtil.isEmpty(listFromExcel)) {
//            resmap.put("message", "未获取到数据");
//            return resmap;
//        }
//        List<GysQueryVo> gysList = purchaseManagementReadMapper.queryList();
//        for (Map map : listFromExcel) {
//            WlAddVo addVo = new WlAddVo();
//            for(GysQueryVo gys : gysList){
//                String gysName = (String) map.get("gys");
//                if(gysName !=null && gysName.equals(gys.getGys())){
//                    addVo.setGysid(gys.getGysid());
//                }else{
//                    resmap.put("message",map.get("name")+"的供应商不存在");
//                    return resmap;
//                }
//            }
//            addVo.setName((String) map.get("name"));
//            addVo.setStandard((String) map.get("standard"));
//            addVo.setType((String) map.get("type"));
//            addVo.setUnit((String) map.get("unit"));
//            list.add(addVo);
//
//        }
//        return resmap;
//    }

}
