package com.daishaowen.test.shejimoshi.decoratorModel;

/**
 * Created by disvenk.dai on 2018-11-05 16:14
 * 抽象组件，被装饰对象的基类
 */
public abstract class Pancake {
    public String desc = "我不是一个具体的煎饼";

    public String getDesc() {
                 return desc;
             }

    public abstract double price();
}
