package com.yinhai.bysj.datavisualization.entity;

import java.io.Serializable;

public class Total implements Serializable {

    public static final long serialVersionUID = 0L;

    private String month;

    private Integer num;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
