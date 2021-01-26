package com.yinhai.bysj.purchasemanagement.vo;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.io.Serializable;

/**
* 生产计划(Plan)实体类
*
* @author default
* @since 2020-12-14 14:43:46
*/
public class PlanAddVo implements Serializable {

    private static final long serialVersionUID = 689305355359913982L;
    //品号
    @NotBlank(message = "品号不能为空")
    private String id;
    //数量
    @NotBlank(message = "数量不能为空")
    private Integer num;
    //录入时间
    private Date inputDate;
    //序号
    private Integer index;
    //是否计算
    private String isCompute;
    //计算时间
    private Date computeDate;


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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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


}
