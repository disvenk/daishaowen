package com.daishaowen.test.shejimoshi.decoratorModel;

/**
 * Created by disvenk.dai on 2018-11-05 16:15
 * 具体组件，具体被装饰对象
 */
public class Roujiamo extends Pancake {
    public Roujiamo() {
        desc = "肉夹馍";
    }

    @Override
    public double price() {
        return 6;
    }
}
