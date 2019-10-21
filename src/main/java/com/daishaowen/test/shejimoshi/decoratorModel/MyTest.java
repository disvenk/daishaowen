package com.daishaowen.test.shejimoshi.decoratorModel;

/**
 * Created by disvenk.dai on 2018-11-05 16:18
 */
public class MyTest {

    public static void main(String[] args) {
        Pancake tornCake = new TornCake();
        //手抓饼基础价
        System.out.println(String.format("%s ￥%s", tornCake.getDesc(), tornCake.price()));

        Pancake roujiamo = new Roujiamo();
        roujiamo = new FiredEgg(roujiamo);//装饰后这个类不再是Roujiamo，而是转变为FiredEgg
        roujiamo = new Ham(roujiamo);//同理

        //我好饿
        System.out.println(String.format("%s ￥%s", roujiamo.getDesc(), roujiamo.price()));
    }
}
