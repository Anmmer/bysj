package com.yinhai.bysj.datavisualization.entity;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Limit {
    @NotBlank(message = "开始时间不能为空")
    private Date startDate;
    @NotBlank(message = "结束时间不能为空")
    private Date endDate;
    private String name;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
