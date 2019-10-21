package com.daishaowen.test.neibulei;

public class Chengyuanneibulei {

    public static class Heat{//由于心脏类不能让人随便访问，所以使用private修饰
        public static void operator(){
            System.out.println("心脏搭桥");
        }
    }

    Heat h = new Heat();

    public void show(){
        //在最外部的类中访问它里面内部类的成员要在外部类的成员位置或者局部创建内部类的对象
        //Heat h = new Heat();
        h.operator();
    }
}
