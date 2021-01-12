package com.yinhai.bysj.basicdata.vo;

import java.io.Serializable;

/**
* BOM资料(Bom)实体类
*
* @author default
* @since 2020-11-27 16:03:40
*/
public class BomAddVo implements Serializable {

    private static final long serialVersionUID = 629225563464358739L;
            //主件id
    private String mid;
            //子件id
    private String sid;
            //子件数量
    private Integer num;
                    
public String getMid() {
return mid;
}

public void setMid(String mid) {
this.mid = mid;
}
                
public String getSid() {
return sid;
}

public void setSid(String sid) {
this.sid = sid;
}
                
public Integer getNum() {
return num;
}

public void setNum(Integer num) {
this.num = num;
}
    
}