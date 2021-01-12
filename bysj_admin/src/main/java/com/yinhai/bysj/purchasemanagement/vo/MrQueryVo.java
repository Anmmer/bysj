package com.yinhai.bysj.purchasemanagement.vo;

import java.io.Serializable;
import java.util.Date;

/**
* (Mr)实体类
*
* @author default
* @since 2020-12-15 11:24:26
*/
public class MrQueryVo implements Serializable {

    private static final long serialVersionUID = 771048850877918365L;
            //序号
    private Integer index;
            //产品品号
    private String id;
            //产品品名
    private String name;

    private Date startDate;

    private Date endDate;
            //子件数量
    private Integer num;
            //单位
    private String unit;
            //物料品号
    private String sid;
            //物料品名
    private String sname;
            //物料单位
    private String sunit;
            //数量
    private Integer pnum;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

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
