package com.yinhai.bysj.datavisualization.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class DataObject implements Serializable {
    private static int i = 0;
    private String word = " ";

    public int getI() {
        return i;
    }

    public void setI(int i) {
        DataObject.i = i;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public static void main(String[] args) {
//        DataObject dataObject = new DataObject();
//        dataObject.setI(1);
//        dataObject.setWord("123");
//        System.out.println(dataObject.getI()+dataObject.getWord());

    }
}
