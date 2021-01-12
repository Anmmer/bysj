package com.yinhai.bysj.consumptionstatistics.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 原料消耗统计(ConsumptionStatistics)实体类
 *
 * @author default
 * @since 2020-12-29 14:43:13
 */
public class ConsumptionStatisticsQueryVo implements Serializable {

    private static final long serialVersionUID = 906536510563181652L;
    //品号
    private String id;
    //数量
    private Integer num;

    private String name;

    //录入时间
    private Date startDate;

    private Date endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
