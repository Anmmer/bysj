package com.yinhai.bysj.basicdata.common;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BomTreeNode implements Serializable {
    private static final long serialVersionUID = -56344539073676005L;
    private String id;
    private String label;
    private String name;
    private String unit;
    private Integer num;
    private List<BomTreeNode> children = new ArrayList<>();

    public BomTreeNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public List<BomTreeNode> getChildren() {
        return children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setChildren(List<BomTreeNode> children) {
        this.children = children;
    }


}
