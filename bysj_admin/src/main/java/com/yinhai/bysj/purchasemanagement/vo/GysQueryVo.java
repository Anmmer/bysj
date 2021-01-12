package com.yinhai.bysj.purchasemanagement.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 供应商(Gys)实体类
 *
 * @author default
 * @since 2020-11-26 14:16:11
 */
public class GysQueryVo implements Serializable {

    private static final long serialVersionUID = 836710549031237383L;
    //供应商id
    private String gysid;
    //供应商
    private String gys;
    //录入时间
    private Timestamp inputDate;

    public void setInputDate(Timestamp inputDate) {
        this.inputDate = inputDate;
    }

    public Timestamp getInputDate() {
        return inputDate;
    }

    public String getGysid() {
        return gysid;
    }

    public void setGysid(String gysid) {
        this.gysid = gysid;
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

}
