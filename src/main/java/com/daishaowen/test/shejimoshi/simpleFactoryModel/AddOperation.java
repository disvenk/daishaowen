package com.daishaowen.test.shejimoshi.simpleFactoryModel;

/**
 * Created by disvenk.dai on 2018-11-07 17:43
 */
public class AddOperation implements Operation {
    @Override
    public float getResult(float firstNumber, float secondNumber) {
        return firstNumber+secondNumber;
    }
}
