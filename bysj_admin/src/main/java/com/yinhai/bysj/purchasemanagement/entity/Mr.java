package com.yinhai.bysj.purchasemanagement.entity;

import java.io.Serializable;

/**
* (Mr)实体类
*
* @author default
* @since 2020-12-15 11:24:26
*/
public class Mr implements Serializable {
    private static final long serialVersionUID = 794290683220555242L;
            //序号
    private Integer index;
            //产品品号
    private String id;
            //产品品名
    private String name;
            //单位
    private String unit;
            //物料品号
    private String sid;
            //物料品名
    private String sname;
            //物料单位
    private String sunit;
            //数量
    private Integer num;
    
                
public Integer getIndex() {
return index;
}

public void setIndex(Integer index) {
this.index = index;
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
                
public String getUnit() {
return unit;
}

public void setUnit(String unit) {
this.unit = unit;
}
                
public String getSid() {
return sid;
}

public void setSid(String sid) {
this.sid = sid;
}
                
public String getSname() {
return sname;
}

public void setSname(String sname) {
this.sname = sname;
}
                
public String getSunit() {
return sunit;
}

public void setSunit(String sunit) {
this.sunit = sunit;
}
                
public Integer getNum() {
return num;
}

public void setNum(Integer num) {
this.num = num;
}
    
}