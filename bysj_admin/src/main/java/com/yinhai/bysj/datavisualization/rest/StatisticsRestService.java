package com.yinhai.bysj.datavisualization.rest;

import com.yinhai.bysj.basicdata.common.BomTreeNode;
import com.yinhai.bysj.basicdata.service.read.BomReadService;
import com.yinhai.bysj.basicdata.service.read.basicInfoReadService;
import com.yinhai.bysj.basicdata.vo.WlInfoVo;
import com.yinhai.bysj.basicdata.vo.WlQueryVo;
import com.yinhai.bysj.datavisualization.common.Utils;
import com.yinhai.bysj.datavisualization.entity.*;
import com.yinhai.bysj.datavisualization.service.read.StatisticsService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.*;

@Validated
@RestService("Statistics")
public class StatisticsRestService extends BaseRestService {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private basicInfoReadService basicInfoReadService;
    @Autowired
    private BomReadService bomReadService;

    @PostMapping("materialConsumData")
    public void materialConsumData(Limit limit) {
        limit.setType("原材料");
        List<Total> materialTotal = statisticsService.queryMaterialConsum(limit);
        limit.setType("辅助材料");
        List<Total> fTotal = statisticsService.queryMaterialConsum(limit);
        BarChart barchartY = new BarChart("原材料", "line");
        BarChart barChartF = new BarChart("辅助材料", "line");
        for (Total total : materialTotal) {
            barchartY.getData()[Integer.valueOf(total.getMonth())-1] = total.getNum();
        }
        for (Total total : fTotal) {
            barChartF.getData()[Integer.valueOf(total.getMonth())-1] = total.getNum();
        }
        List<BarChart> list = new ArrayList<>();
        list.add(barchartY);
        list.add(barChartF);
        setData("list",list);
    }

    @PostMapping("materielPieChart")
    public void materielPieChart(){
        setData("list",statisticsService.queryCountByType());
    }

    @PostMapping("materialMoneyData")
    public void materialMoneyData(Limit limit){
        limit.setType("辅助材料");
        List<Total> mTotal = statisticsService.queryMaterialMoney(limit);
        limit.setType("原材料");
        List<Total> fTotal = statisticsService.queryMaterialMoney(limit);
        BarChart barChartY = new BarChart("原材料", "bar");
        BarChart barChartF = new BarChart("辅助材料", "bar");
        for (Total total : mTotal) {
            barChartY.getData()[Integer.valueOf(total.getMonth())-1] = total.getNum();
        }
        for (Total total : fTotal) {
            barChartF.getData()[Integer.valueOf(total.getMonth())-1] = total.getNum();
        }
        List<BarChart> list = new ArrayList<>();
        list.add(barChartY);
        list.add(barChartF);
        setData("list",list);
    }

    @PostMapping("product3DChart")
    public void product3DChart(Limit limit){
        WlQueryVo wlQueryVo = new WlQueryVo();
        wlQueryVo.setType("产成品");
        wlQueryVo.setIsputbom("是");
        List<WlInfoVo> wlList = basicInfoReadService.queryWlInfoList(wlQueryVo);
        Map<String, Consumption> map = new HashMap<>();
        for (WlInfoVo wl:wlList) {
            map.put(wl.getId(),new Consumption(wl.getId(),wl.getName()));
        }
        List<Consumption> consumptions = statisticsService.queryProduct3DChart(limit);
        Integer max = 0;
        for (Consumption con:consumptions) {
            if(map.get(con.getId())!=null){
                map.get(con.getId()).getArray()[Integer.valueOf(con.getMonth())-1]=con.getNum();
                if(con.getNum()>max){
                    max=con.getNum();
                }
            }
        }
        List<Integer[]> list = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for(int i = 0;i<wlList.size();i++){
            nameList.add(wlList.get(i).getName());
            for(int j = 0;j<12;j++){
                Integer[] array = {i,j,map.get(wlList.get(i).getId()).getArray()[j]};
                list.add(array);
            }
        }
        setData("max",max*1.2);
        setData("list",list);
        setData("nameList",nameList);
    }

    @PostMapping("funnelSupplierData")
    public void funnelSupplierData(Limit limit){
        List<PieChart> list =statisticsService.funnelSupplierChart(limit);
        List<String> names = new ArrayList<>();
        for(PieChart chart : list){
            names.add(chart.getName());
        }
        setData("list",list);
    }

    @PostMapping("productAmountChartData")
    public void productAmountChartData(Limit limit){
        List<Consumption> list = statisticsService.productNum(limit);
        WlQueryVo wlQueryVo = new WlQueryVo();
        wlQueryVo.setType("产成品");
        wlQueryVo.setIsputbom("是");
        List<WlInfoVo> wlInfoVos = basicInfoReadService.queryWlInfoList(wlQueryVo);
        StackingDiagram bD = new StackingDiagram("半成品加工费用", "line", "总量");
        StackingDiagram yD = new StackingDiagram("原材料费用", "line", "总量");
        StackingDiagram fD = new StackingDiagram("辅助材料费用", "line", "总量");
        List<Consumption> list1 = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (WlInfoVo wl : wlInfoVos) {
            Integer i = 0;
            for (Consumption c : list) {
                if (wl.getName().equals(c.getName())) {
                    i = c.getNum();
                }
            }
            nameList.add(wl.getName());
            list1.add(new Consumption(wl.getId(), wl.getName(), i));
        }
        for (Consumption c : list1) {
            Map<String, BomTreeNode> map = bomReadService.queryBomTreeList();
            BomTreeNode bomTreeNode = map.get(c.getId());
            BigDecimal z = new BigDecimal(0);
            BigDecimal y = new BigDecimal(0);
            BigDecimal f = new BigDecimal(0);
            for (int i = 0; i < bomTreeNode.getChildren().size(); i++) {
                if (bomTreeNode.getChildren().get(i).getChildren().size() != 0) {
                    z=z.add(new BigDecimal(c.getNum()*bomTreeNode.getChildren().get(i).getNum()).multiply(bomTreeNode.getChildren().get(i).getPrice()));
                    List<BomTreeNode> children = bomTreeNode.getChildren().get(i).getChildren();
                    for (BomTreeNode bomTreeNode1 : children) {
                        if (bomTreeNode1.getType().equals("原材料")) {
                            y=y.add(new BigDecimal(c.getNum() * bomTreeNode.getChildren().get(i).getNum() * bomTreeNode1.getNum()).multiply(bomTreeNode1.getPrice()));
                        }else if(bomTreeNode1.getType().equals("辅助材料")){
                            f=f.add(new BigDecimal(c.getNum() * bomTreeNode.getChildren().get(i).getNum() * bomTreeNode1.getNum()).multiply(bomTreeNode1.getPrice()));
                        }
                    }
                } else {
                    if(bomTreeNode.getChildren().get(i).getType().equals("原材料")){
                        y=y.add(new BigDecimal(c.getNum()*bomTreeNode.getChildren().get(i).getNum()).multiply(bomTreeNode.getChildren().get(i).getPrice()));
                    }else if(bomTreeNode.getChildren().get(i).getType().equals("辅助材料")){
                        f=f.add(new BigDecimal(c.getNum()*bomTreeNode.getChildren().get(i).getNum()).multiply(bomTreeNode.getChildren().get(i).getPrice()));
                    }
                }
            }
            bD.getData().add(z);
            yD.getData().add(y);
            fD.getData().add(f);
        }
        List<StackingDiagram> stackingDiagrams = new ArrayList<>();
        stackingDiagrams.add(bD);
        stackingDiagrams.add(yD);
        stackingDiagrams.add(fD);
        setData("list",stackingDiagrams);
        setData("nameList",nameList);
    }

    @PostMapping("productSelfChartData")
    public void productSelfChartData(Limit limit){
        List<Total> totals = statisticsService.queryProductByMonth(limit);
        BarChart barChart1 = new BarChart("产量","bar");
        AmountBarChart barChart2 = new AmountBarChart("生产费用","bar");
        for(Total total : totals){
            Map<String, BomTreeNode> map = bomReadService.queryBomTreeList();
            BomTreeNode bomTreeNode = map.get(total.getId());
            for (int i = 0; i < bomTreeNode.getChildren().size(); i++) {
                if (bomTreeNode.getChildren().get(i).getChildren().size() != 0) {
                    barChart1.getData()[Integer.valueOf(total.getMonth())-1]+=total.getNum();
                    barChart2.getData()[Integer.valueOf(total.getMonth())-1]=(new BigDecimal(total.getNum()).multiply(bomTreeNode.getChildren().get(i).getPrice())).add(barChart2.getData()[Integer.valueOf(total.getMonth())-1]);
                    }
                }
            }
        setData("char1Max", Math.ceil(Utils.Max(barChart1.getData())*2));
        setData("Char2Max",Utils.Max(barChart2.getData()).multiply(new BigDecimal(1.3)).setScale(0,BigDecimal.ROUND_UP));
        setData("barChar1",barChart1);
        setData("barChar2",barChart2);
        }

}
