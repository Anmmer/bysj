package com.yinhai.bysj.datavisualization.common;

import java.io.Serializable;

public class DataObject implements Serializable {
    private static int i = 0;
    private String word = " ";

    public static final String a;
    public static final String b;

    {
        System.out.println("construct");
    }

    static {
        System.out.println("static");
        a = "123";
        b = "456";
    }

    public static void sout(){
        System.out.println("sout");
        String s = "";
    }

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
//        String c = "123456";
//        String d = a + b;
//        System.out.println(c == d);
        DataObject.sout();
    }
}
