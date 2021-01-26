package com.yinhai.bysj.purchasemanagement.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

/**
 * 生产计划(Plan)实体类
 *
 * @author default
 * @since 2020-12-14 14:43:46
 */
public class PlanEditVo implements Serializable {

    private static final long serialVersionUID = -20122490649349285L;
    //品号

    private String id;
    //数量
    private Integer num;
    //录入时间
    private Date inputDate;
    //序号
    @NotNull(message = "序号不能为空")
    private Integer inde;
    //是否计算
    private String isCompute;

    public Integer getInde() {
        return inde;
    }

    public void setInde(Integer inde) {
        this.inde = inde;
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
