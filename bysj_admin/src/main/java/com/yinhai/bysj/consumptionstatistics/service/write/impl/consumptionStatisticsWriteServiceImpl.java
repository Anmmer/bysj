package com.yinhai.bysj.consumptionstatistics.service.write.impl;

import com.yinhai.bysj.consumptionstatistics.entity.ConsumptionStatistics;
import com.yinhai.bysj.consumptionstatistics.entity.FinishedProduct;
import com.yinhai.bysj.consumptionstatistics.mapper.write.consumptionStatisticsWriteMapper;
import com.yinhai.bysj.consumptionstatistics.service.write.consumptionStatisticsWriteService;
import com.yinhai.bysj.consumptionstatistics.service.write.finishedProductWriteService;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsQueryVo;
import com.yinhai.bysj.consumptionstatistics.vo.FinishedProductInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yinhai.ta404.core.transaction.annotation.TaTransactional;
import com.yinhai.ta404.core.service.BaseService;
import com.yinhai.ta404.core.exception.AppException;
import com.yinhai.bysj.consumptionstatistics.vo.ConsumptionStatisticsAddVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 原料消耗统计(ConsumptionStatistics)表服务实现类
 *
 * @author default
 * @since 2020-12-29 14:43:13
 */
@Service
@TaTransactional
public class consumptionStatisticsWriteServiceImpl extends BaseService implements consumptionStatisticsWriteService {
    @Resource
    private consumptionStatisticsWriteMapper consumptionStatisticsWriteMapper;
    @Autowired
    private finishedProductWriteService finishedProductWriteService;

    /**
     * 新增数据
     *
     * @return 实例对象
     */
    @Override
    public int addConsumptionStatisticsInfo(List<ConsumptionStatisticsAddVo> list, List<FinishedProductInfoVo> finishedProductInfoVoList) {
        List<ConsumptionStatistics> consumptionStatisticsList = new ArrayList<>();
        List<FinishedProduct> finishedProductList = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            ConsumptionStatistics consumptionStatistics = new ConsumptionStatistics();
            consumptionStatistics.setId(list.get(j).getId());
            consumptionStatistics.setNum(list.get(j).getNum());
            consumptionStatistics.setInputDate(getSysDate());
            consumptionStatisticsList.add(consumptionStatistics);
        }
        for (int k = 0; k < finishedProductInfoVoList.size(); k++) {
            FinishedProduct finishedProduct = new FinishedProduct();
            finishedProduct.setInde(finishedProductInfoVoList.get(k).getInde());
            finishedProduct.setComputeDate(getSysDate());
            finishedProduct.setIsCompute("是");
            finishedProductList.add(finishedProduct);
        }
        ConsumptionStatisticsQueryVo consumptionStatisticsQueryVo = new ConsumptionStatisticsQueryVo();
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        consumptionStatisticsQueryVo.setStartDate(cale.getTime());
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        consumptionStatisticsQueryVo.setEndDate(cale.getTime());
        finishedProductWriteService.batchUpdate(finishedProductList);
        consumptionStatisticsWriteMapper.deleteByDate(consumptionStatisticsQueryVo);
        int i = consumptionStatisticsWriteMapper.insert(consumptionStatisticsList);
        if (i == 0) {
            throw new AppException("未添加任何数据，请重试");
        }
        return i;
    }


}
