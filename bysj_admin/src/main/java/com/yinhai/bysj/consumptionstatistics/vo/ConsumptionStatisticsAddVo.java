package com.yinhai.bysj.consumptionstatistics.vo;

import java.io.Serializable;
import java.util.Date;

/**
* 原料消耗统计(ConsumptionStatistics)实体类
*
* @author default
* @since 2020-12-29 14:43:13
*/
public class ConsumptionStatisticsAddVo implements Serializable {

    private static final long serialVersionUID = 978278129318822024L;

    private String id;
            //数量
    private Integer num;

    private Date inputDate;

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
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

}
