package com.yinhai.bysj.purchasemanagement.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 生产计划(Plan)实体类
*
* @author default
* @since 2020-12-16 16:10:04
*/
public class Plan implements Serializable {
    private static final long serialVersionUID = 215957869413060922L;
            //品号
    private String id;
            //数量
    private Integer num;
            //录入时间
    private Date inputDate;
            //序号
    private Integer inde;
            //是否计算
    private String isCompute;
            //计算时间
    private Date computeDate;

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

public Date getComputeDate() {
return computeDate;
}

public void setComputeDate(Date computeDate) {
this.computeDate = computeDate;
}

}
