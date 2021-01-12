package com.yinhai.bysj.basicdata.service.read.impl;

import com.yinhai.bysj.basicdata.entity.Wl;
import com.yinhai.bysj.basicdata.mapper.read.basicInfoReadMapper;
import com.yinhai.bysj.basicdata.service.read.basicInfoReadService;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import com.yinhai.bysj.basicdata.vo.WlQueryVo;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.NoTransactional;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;

import javax.annotation.Resource;
import java.util.List;

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

}
