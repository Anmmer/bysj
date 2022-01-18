package com.yinhai.bysj.basicdata.common;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BomTreeNode implements Serializable {
    private static final long serialVersionUID = -56344539073676005L;
    private String id;
    private String label;
    private String name;
    private String unit;
    private Integer num;
    private String type;
    private BigDecimal price;
    private List<BomTreeNode> children = new ArrayList<>();

    public BomTreeNode() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
