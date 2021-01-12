package com.yinhai.bysj.consumptionstatistics.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 已生产产成品(FinishedProduct)实体类
*
* @author default
* @since 2020-12-25 17:09:54
*/
public class FinishedProduct implements Serializable {
    private static final long serialVersionUID = -36524072140005468L;
            //已生成物料ID
    private String id;
            //数量
    private Integer num;
            //录入时间
    private Date inputDate;
            //是否原料消耗计算
    private String isCompute;
            //计算时间
    private Date computeDate;
            //序号
    private Integer inde;
    
                
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
                
public Integer getInde() {
return inde;
}

public void setInde(Integer inde) {
this.inde = inde;
}
    
}