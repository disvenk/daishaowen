package com.daishaowen.test.shejimoshi.simpleFactoryModel;

/**
 * Created by disvenk.dai on 2018-11-07 17:44
 */
public class MulOperation implements Operation {
    @Override
    public float getResult(float firstNumber, float secondNumber) {
        return firstNumber*secondNumber;
    }
}
