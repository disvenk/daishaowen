package com.daishaowen.test.shejimoshi.decoratorModel;

/**
 * Created by disvenk.dai on 2018-11-05 16:15
 */
public class TornCake extends Pancake {
    public TornCake() {
        desc = "手抓饼";
    }

    @Override
    public double price() {
        return 4;
    }
}
