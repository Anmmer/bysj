package com.yinhai.bysj.datavisualization.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StackingDiagram implements Serializable {

    public static final long serialVersionUID = 0L;

    private String id;
    private String name;
    private Integer num;
    private String type;
    private String stack;

    private List<BigDecimal> data = new ArrayList<>();

    public StackingDiagram() {
    }

    public StackingDiagram(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public StackingDiagram(String name, String type, String stack) {
        this.name = name;
        this.type = type;
        this.stack = stack;
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

    public List<BigDecimal> getData() {
        return data;
    }

    public void setData(List<BigDecimal> data) {
        this.data = data;
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

}
