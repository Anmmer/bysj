package com.yinhai.bysj.consumptionstatistics.rest;

import com.yinhai.bysj.basicdata.common.BomTreeNode;
import com.yinhai.bysj.basicdata.service.read.BomReadService;
import com.yinhai.bysj.consumptionstatistics.service.read.consumptionStatisticsReadService;
import com.yinhai.bysj.consumptionstatistics.service.read.finishedProductReadService;
import com.yinhai.bysj.consumptionstatistics.service.write.consumptionStatisticsWriteService;
import com.yinhai.bysj.consumptionstatistics.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.requestbean.PageParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

/**
 * 原料消耗统计(ConsumptionStatistics)表控制层
 *
 * @author default
 * @since 2020-12-29 14:43:13
 */
@Validated
@RestService("consumptionStatistics")
public class consumptionStatisticsRestService extends BaseRestService {
    /**
     * 服务对象
     */
    @Resource
    private consumptionStatisticsReadService consumptionStatisticsReadService;
    @Resource
    private consumptionStatisticsWriteService consumptionStatisticsWriteService;
    @Autowired
    private BomReadService bomReadService;
    @Autowired
    private finishedProductReadService finishedProductService;

    /**
     * 新增数据
     */
    @PostMapping("addConsumptionStatisticsInfo")
    public void addConsumptionStatisticsInfo(FinishedProductQueryVo finishedProductQueryVo) {
        List<ConsumptionStatisticsAddVo> list = new ArrayList<>();
        Map<String, Integer> index = new HashMap<>();
        int a = 0;
        finishedProductQueryVo.setIsCompute("否");
        List<FinishedProductInfoVo> finishedProductInfoVoList = finishedProductService.queryFinishedProductInfoList(finishedProductQueryVo);
        Map<String, BomTreeNode> map = bomReadService.queryBomTreeList();
        for (int i = 0; i < finishedProductInfoVoList.size(); i++) {
            FinishedProductInfoVo finishedProductInfoVo = finishedProductInfoVoList.get(i);
            if(map.get(finishedProductInfoVoList.get(i).getId())==null){
                continue;
            }
            BomTreeNode bomTreeNode = map.get(finishedProductInfoVoList.get(i).getId());
            List<BomTreeNode> children;
            for (int j = 0; j < bomTreeNode.getChildren().size(); j++) {
                if (bomTreeNode.getChildren().get(j).getChildren().size() == 0) {//两层
                    BomTreeNode bomTreeNode1 = bomTreeNode.getChildren().get(j);
                    if (index.get(bomTreeNode1.getId()) != null) {
                        list.get(index.get(bomTreeNode1.getId())).setNum(list.get(index.get(bomTreeNode1.getId())).getNum() + finishedProductInfoVo.getNum() * bomTreeNode1.getNum());
                    } else {
                        ConsumptionStatisticsAddVo consumptionStatisticsAddVo = new ConsumptionStatisticsAddVo();
                        consumptionStatisticsAddVo.setNum(finishedProductInfoVo.getNum() * bomTreeNode1.getNum());
                        consumptionStatisticsAddVo.setId(bomTreeNode1.getId());
                        list.add(consumptionStatisticsAddVo);
                        index.put(consumptionStatisticsAddVo.getId(), a);
                        a++;
                    }
                } else {
                    children = bomTreeNode.getChildren().get(j).getChildren();//三层
                    for (int k = 0; k < children.size(); k++) {
                        if (index.get(children.get(k).getId()) != null) {
                            list.get(index.get(children.get(k).getId())).setNum(list.get(index.get(children.get(k).getId())).getNum() + children.get(k).getNum() * bomTreeNode.getChildren().get(j).getNum() * finishedProductInfoVo.getNum());
                        } else {
                            ConsumptionStatisticsAddVo consumptionStatisticsAddVo = new ConsumptionStatisticsAddVo();
                            consumptionStatisticsAddVo.setId(children.get(k).getId());
                            consumptionStatisticsAddVo.setNum(children.get(k).getNum() * bomTreeNode.getChildren().get(j).getNum() * finishedProductInfoVo.getNum());
                            list.add(consumptionStatisticsAddVo);
                            index.put(consumptionStatisticsAddVo.getId(), a);
                            a++;
                        }
                    }
                }
            }
        }
        consumptionStatisticsWriteService.addConsumptionStatisticsInfo(list,finishedProductInfoVoList);
    }

    /**
     * 根据查询条件查询数据
     *
     * @param consumptionStatisticsQueryVo
     */
    @PostMapping("queryConsumptionStatisticsInfoList")
    public void queryConsumptionStatisticsInfoList(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo) {
        setData("consumptionStatisticsList", consumptionStatisticsReadService.queryConsumptionStatisticsInfoList(consumptionStatisticsQueryVo));
    }

    /**
     * 根据查询条件分页查询数据
     *
     * @param consumptionStatisticsQueryVo
     * @param pageParam
     */
    @PostMapping("queryConsumptionStatisticsInfoPage")
    public void queryConsumptionStatisticsInfoPage(ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo, PageParam pageParam) {
        setPageBean(consumptionStatisticsReadService.queryConsumptionStatisticsInfoPage(consumptionStatisticsQueryVo, pageParam));
    }
}
