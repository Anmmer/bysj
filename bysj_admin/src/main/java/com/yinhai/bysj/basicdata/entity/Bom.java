package com.yinhai.bysj.basicdata.entity;

import java.io.Serializable;

/**
 * BOM资料(Bom)实体类
 *
 * @author default
 * @since 2020-11-27 16:03:40
 */
public class Bom implements Serializable {
    private static final long serialVersionUID = 1L;
    //序号
    private Integer index;
    //主件id
    private String mid;
    //子件id
    private String sid;
    //子件数量
    private Integer num;


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Bom{" +
                "index=" + index +
                ", mid='" + mid + '\'' +
                ", sid='" + sid + '\'' +
                ", num=" + num +
                '}';
    }

}
