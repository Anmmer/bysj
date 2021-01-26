package com.yinhai.bysj.basicdata.vo;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
* 物料表(Wl)实体类
*
* @author default
* @since 2020-11-29 11:23:27
*/
public class WlAddVo implements Serializable {

    private static final long serialVersionUID = -42303453004928341L;
            //货品名称
            @NotBlank(message = "名称不能为空")
    private String name;
            //规格
            @NotBlank(message = "规格不能为空")
    private String standard;
            //货品类别
            @NotBlank(message = "类别不能为空")
    private String type;
            //单位
            @NotBlank(message = "单位不能为空")
    private String unit;
            //供应商ID
            @NotBlank(message = "供应商不能为空")
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

public String getGysid() {
return gysid;
}

public void setGysid(String gysid) {
this.gysid = gysid;
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
