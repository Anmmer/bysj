package com.yinhai.bysj.basicdata.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Constant {
    public static final String B1="原材料";
    public static final String B2="辅助材料";
    public static final String B3="自制半成品";
    public static final String B4="成品";

    public static String getOrderIdByTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result="";
        Random random = new Random();
        int i =random.nextInt();
        if(i<0){
            i=-i;
        }
        result+=String.format("%11s",i).replace(' ','0');
        return newDate+result;
    }

    public static void main(String[] args) {
//        System.out.println(Constant.getOrderIdByTime());
        System.out.println(Integer.valueOf("01").equals(1));
    }

}
