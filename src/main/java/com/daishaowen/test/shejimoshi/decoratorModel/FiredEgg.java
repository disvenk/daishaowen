package com.daishaowen.test.shejimoshi.decoratorModel;

/**
 * Created by disvenk.dai on 2018-11-05 16:16
 * 具体装饰，具体装饰者
 */
public class FiredEgg extends Condiment {
    private Pancake pancake;

    public FiredEgg(Pancake pancake) {
        this.pancake = pancake;
    }

    @Override
    public String getDesc() {
        return pancake.getDesc() + ", 煎蛋";
    }

    @Override
    public double price() {
        return pancake.price() + 2;
    }
}
