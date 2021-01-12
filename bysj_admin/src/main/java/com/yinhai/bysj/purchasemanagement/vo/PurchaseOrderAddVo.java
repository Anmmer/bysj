package com.yinhai.bysj.purchasemanagement.vo;

import java.util.Date;
import java.io.Serializable;

/**
* 采购订单(PurchaseOrder)实体类
*
* @author default
* @since 2020-12-21 11:02:22
*/
public class PurchaseOrderAddVo implements Serializable {

    private static final long serialVersionUID = -40578619501039349L;
            //生成日期
    private Date inputDate;
            //是否到货
    private String isDelivered;
            //到货日期
    private Date deliveredDate;
            //物料id
    private String id;
            //数量
    private Integer num;
                    
public Date getInputDate() {
return inputDate;
}

public void setInputDate(Date inputDate) {
this.inputDate = inputDate;
}
                
public String getIsDelivered() {
return isDelivered;
}

public void setIsDelivered(String isDelivered) {
this.isDelivered = isDelivered;
}
                
public Date getDeliveredDate() {
return deliveredDate;
}

public void setDeliveredDate(Date deliveredDate) {
this.deliveredDate = deliveredDate;
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