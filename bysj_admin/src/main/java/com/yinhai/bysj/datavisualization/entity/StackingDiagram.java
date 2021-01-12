package com.yinhai.bysj.datavisualization.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

public class StackingDiagram implements Serializable {

    public static final long serialVersionUID = 0L;

    private String name;
    private String type;
    private String stack;
    private Integer[] data = {0,0,0,0,0,0,0,0,0,0,0,0};

    public StackingDiagram(String name, String type, String stack) {
        this.name = name;
        this.type = type;
        this.stack = stack;
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
