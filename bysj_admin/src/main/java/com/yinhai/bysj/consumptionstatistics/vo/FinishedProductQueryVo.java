package com.yinhai.bysj.consumptionstatistics.vo;

import java.util.Date;
import java.io.Serializable;

/**
* 已生产产成品(FinishedProduct)实体类
*
* @author default
* @since 2020-12-25 17:09:58
*/
public class FinishedProductQueryVo implements Serializable {

    private static final long serialVersionUID = 910477482480233078L;
            //已生成物料ID
    private String id;

    private String name;
            //数量
    private Integer num;
            //录入时间
    private Date startDate;

    private Date endDate;
            //是否原料消耗计算
    private String isCompute;
            //计算时间
    private Date computeDate;
            //序号
    private Integer inde;

    public Date getStartDate() {
        return startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public Integer getNum() {
return num;
}

public void setNum(Integer num) {
this.num = num;
}

public String getIsCompute() {
return isCompute;
}

public void setIsCompute(String isCompute) {
this.isCompute = isCompute;
}

public Date getComputeDate() {
return computeDate;
}

public void setComputeDate(Date computeDate) {
this.computeDate = computeDate;
}

public Integer getInde() {
return inde;
}

public void setInde(Integer inde) {
this.inde = inde;
}

}
