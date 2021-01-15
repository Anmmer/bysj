package com.yinhai.bysj.datavisualization.entity;

import java.io.Serializable;

public class Consumption implements Serializable {

    public static final long serialVersionUID = 0L;
    //编号
    private String id;
    private String name;
    private Integer num;
    private String month;
    //一月
    private Integer[] array ={0,0,0,0,0,0,0,0,0,0,0,0};

    public Consumption() {
    }

    public Consumption(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Consumption(String id, String name, Integer num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getArray() {
        return array;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }
}
