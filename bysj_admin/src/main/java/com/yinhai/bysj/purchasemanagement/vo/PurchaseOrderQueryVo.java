package com.yinhai.bysj.purchasemanagement.vo;

import java.util.Date;
import java.io.Serializable;

/**
 * 采购订单(PurchaseOrder)实体类
 *
 * @author default
 * @since 2020-12-21 11:02:22
 */
public class PurchaseOrderQueryVo implements Serializable {

    private static final long serialVersionUID = -31823688946999983L;
    //订单号
    private String orderId;
    //生成日期
    private Date startDate;

    private Date endDate;
    //是否到货
    private String isDelivered;
    //到货日期
    private Date deliveredDate;
    //物料id
    private String id;

    private String sname;

    private String sunit;

    private String gys;
    //数量
    private Integer num;

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

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSunit() {
        return sunit;
    }

    public void setSunit(String sunit) {
        this.sunit = sunit;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
