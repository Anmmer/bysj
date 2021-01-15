package com.yinhai.bysj.datavisualization.entity;


public class BarChart {
    public static final long serialVersionUID = 0L;

    private String id;
    private String name;
    private Integer num;
    private String type;
    private String stack;
    private Integer[] data = {0,0,0,0,0,0,0,0,0,0,0,0};

    public BarChart() {
    }


    public BarChart(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public BarChart(String name, String type, String stack) {
        this.name = name;
        this.type = type;
        this.stack = stack;
    }
    public BarChart(String id, String name, Integer num) {
        this.id = id;
        this.name = name;
        this.num = num;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public Integer[] getData() {
        return data;
    }

    public void setData(Integer[] data) {
        this.data = data;
    }
}
