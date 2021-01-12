package com.yinhai.bysj.consumptionstatistics.vo;

import java.io.Serializable;
import java.util.Date;

/**
* 原料消耗统计(ConsumptionStatistics)实体类
*
* @author default
* @since 2020-12-29 14:43:13
*/
public class ConsumptionStatisticsInfoVo implements Serializable {

    private static final long serialVersionUID = 494917772511554790L;
            //品号
    private String id;
            //数量
    private Integer num;

    private String name;

    private String unit;

    private String type;

    private Date inputDate;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
