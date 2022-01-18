package com.yinhai.bysj.basicdata.service.read;

import com.yinhai.bysj.basicdata.entity.Wl;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import com.yinhai.bysj.basicdata.vo.WlQueryVo;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import com.yinhai.ta404.core.restservice.resultbean.Page;


/**
 * 物料表(Wl)表服务接口
 *
 * @author default
 * @since 2020-11-26 09:31:47
 */
public interface basicInfoReadService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WlInfoVo queryWlInfoById(String id);

    /**
     * 根据查询条件查询数据
     *
     * @param wlQueryVo
     */
    List<WlInfoVo> queryWlInfoList(WlQueryVo wlQueryVo);

    /**
     * 根据查询条件分页查询数据
     *
     * @param wlQueryVo
     * @param pageParam
     */
    Page<WlInfoVo> queryWlInfoPage(WlQueryVo wlQueryVo, PageParam pageParam);

    Page<WlInfoVo> queryMainListPage(WlQueryVo wlQueryVo, PageParam pageParam);

    Page<WlInfoVo> queryRawListPage(WlQueryVo wlQueryVo,PageParam pageParam);

//    Map checkImport(InputStream inputStream) throws Exception;
}
