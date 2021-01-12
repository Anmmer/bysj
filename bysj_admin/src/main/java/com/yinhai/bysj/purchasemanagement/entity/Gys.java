package com.yinhai.bysj.purchasemanagement.entity;

import java.io.Serializable;

/**
* 供应商(Gys)实体类
*
* @author default
* @since 2020-11-26 14:16:09
*/
public class Gys implements Serializable {
    private static final long serialVersionUID = 899891862038126126L;
            //供应商id
    private String gysid;
            //供应商
    private String gys;


public String getId() {
return gysid;
}

public void setId(String id) {
this.gysid = id;
}

public String getGys() {
return gys;
}

public void setGys(String gys) {
this.gys = gys;
}

}
