package com.daishaowen.test.neibulei;

//匿名内部类的使用
public class Outer {
    public void method(){
        new Inner(){
            @Override
            public void show() {
                System.out.println("show");
            }
        }.show();

        Inner inner = new Inner(){
            @Override
            public void show() {
                System.out.println("show");
            }
        };

        inner.show();
    }
}
