package com.yinhai.bysj.datavisualization.rest;

import com.yinhai.bysj.basicdata.service.read.basicInfoReadService;
import com.yinhai.bysj.datavisualization.entity.Limit;
import com.yinhai.bysj.datavisualization.entity.StackingDiagram;
import com.yinhai.bysj.datavisualization.entity.Total;
import com.yinhai.bysj.datavisualization.service.read.StatisticsService;
import com.yinhai.ta404.core.restservice.BaseRestService;
import com.yinhai.ta404.core.restservice.annotation.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestService("Statistics")
public class StatisticsRestService extends BaseRestService {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("materialConsumData")
    public void materialConsumData() {
        Limit limit = new Limit();
        limit.setType("原材料");
        List<Total> materialTotal = statisticsService.queryMaterialConsum(limit);
        limit.setType("辅助材料");
        List<Total> fTotal = statisticsService.queryMaterialConsum(limit);
        StackingDiagram stackingDiagramy = new StackingDiagram("原材料", "line", "总量");
        StackingDiagram stackingDiagramf = new StackingDiagram("辅助材料", "line", "总量");
        for (Total total : materialTotal) {
            stackingDiagramy.getData()[Integer.valueOf(total.getMonth())-1] = total.getNum();
        }
        for (Total total : fTotal) {
            stackingDiagramf.getData()[Integer.valueOf(total.getMonth())-1] = total.getNum();
        }
        List<StackingDiagram> list = new ArrayList<>();
        list.add(stackingDiagramy);
        list.add(stackingDiagramf);
        setData("list",list);
    }

    @PostMapping("materielPieChart")
    public void materielPieChart(){
        setData("list",statisticsService.queryCountByType());
    }
}
