package com.daishaowen.test.neibulei;

public class Test {
    public static void main(String[] args) {
        //在main(别的)方法的类中访问另外一个类中的内部类的成员
        //Chengyuanneibulei.Heat heat = new Chengyuanneibulei().new Heat();

        //成员内部类被静态修饰后的使用方式
        Chengyuanneibulei.Heat heat = new Chengyuanneibulei.Heat();
        Chengyuanneibulei.Heat.operator();
    }
}
