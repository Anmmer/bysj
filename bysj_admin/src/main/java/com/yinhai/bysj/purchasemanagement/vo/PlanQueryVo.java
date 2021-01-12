package com.yinhai.bysj.purchasemanagement.vo;


import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;

/**
* 生产计划(Plan)实体类
*
* @author default
* @since 2020-12-14 14:43:47
*/
public class PlanQueryVo implements Serializable {

    private static final long serialVersionUID = 473508262977914036L;
    private Integer inde;
    //品号
    private String id;

    private String name;
    //数量
    private Integer num;
    //录入时间
    private Date startDate;

    private Date endDate;

    //是否计算
    private String isCompute;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInde() {
        return inde;
    }

    public void setInde(Integer inde) {
        this.inde = inde;
    }

    public String getIsCompute() {
        return isCompute;
    }

    public void setIsCompute(String isCompute) {
        this.isCompute = isCompute;
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
