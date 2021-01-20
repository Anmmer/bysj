package com.yinhai.bysj.datavisualization.common;

import org.aspectj.util.UtilClassLoader;

import java.math.BigDecimal;
import java.util.Arrays;

public class Utils {
    public static int Max(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

    public static Integer Max(Integer[] arr) {
        Integer max = 0;
        for (Integer i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static BigDecimal Max(BigDecimal[] arr){
        BigDecimal max = new BigDecimal(0);
        for(BigDecimal i:arr){
            if(i.compareTo(max)==1){
                max=i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2};
//        BigDecimal[] a ={new BigDecimal(1),new BigDecimal(2)};
//        System.out.println(Utils.Max(a).multiply(new BigDecimal(1.1)).setScale(0,BigDecimal.ROUND_UP));
        System.out.println(Math.ceil(Utils.Max(a)*1.1));
    }
}
