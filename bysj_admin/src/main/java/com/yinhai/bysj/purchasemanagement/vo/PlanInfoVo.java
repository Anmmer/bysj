package com.yinhai.bysj.purchasemanagement.vo;

import java.util.Date;
import java.io.Serializable;

/**
* 生产计划(Plan)实体类
*
* @author default
* @since 2020-12-14 14:43:47
*/
public class PlanInfoVo implements Serializable {

    private static final long serialVersionUID = -11140063067601834L;
    private Integer inde;
            //品号
    private String id;
            //数量
    private Integer num;
            //已生产数量
    private Integer consumeNum;
            //录入时间
    private Date inputDate;
            //计划日期
    private String planDate;

    private String name;

    private String unit;
            //是否计算
    private String isCompute;

    private Date computeDate;

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    public Integer getInde() {
        return inde;
    }

    public void setInde(Integer inde) {
        this.inde = inde;
    }

    public Date getComputeDate() {
        return computeDate;
    }

    public void setComputeDate(Date computeDate) {
        this.computeDate = computeDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

public Date getInputDate() {
return inputDate;
}

public void setInputDate(Date inputDate) {
this.inputDate = inputDate;
}

public String getIsCompute() {
return isCompute;
}

public void setIsCompute(String isCompute) {
this.isCompute = isCompute;
}

}
