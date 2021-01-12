package com.yinhai.bysj.basicdata.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * 物料表(Wl)实体类
 *
 * @author default
 * @since 2020-11-29 11:23:28
 */
public class WlInfoVo implements Serializable {

    private static final long serialVersionUID = -25946603413697381L;

    private String pid;
    //货品ID
    private String id;
    //货品名称
    private String name;
    //规格
    private String standard;
    //货品类别
    private String type;
    //单位
    private String unit;
    //供应商
    private String gys;

    private String gysid;
    //价格录入时间
    private Date pricedate;
    //价格
    private BigDecimal price;
    //BOM录入时间
    private Date bomdate;
    //是否已录入
    private String isputbom;
    //是否已录入
    private String isputprice;

    private Integer num;

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

    public String getPid() {
        return pid;
    }

    public WlInfoVo() {
    }

    public WlInfoVo(String pid, String id, String name,  String unit ,Integer num) {
        this.pid = pid;
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.num = num;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getPricedate() {
        return pricedate;
    }

    public void setPricedate(Date pricedate) {
        this.pricedate = pricedate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getBomdate() {
        return bomdate;
    }

    public void setBomdate(Date bomdate) {
        this.bomdate = bomdate;
    }

    public String getIsputbom() {
        return isputbom;
    }

    public void setIsputbom(String isputbom) {
        this.isputbom = isputbom;
    }

    public String getIsputprice() {
        return isputprice;
    }

    public void setIsputprice(String isputprice) {
        this.isputprice = isputprice;
    }

}
