package com.daishaowen.test.shejimoshi.factoryMethodModel.shujuku;

/**
 * Created by disvenk.dai on 2018-11-08 15:47
 */
public class OperatorOracleFactory implements IFactory {
    @Override
    public Opereator getOperator() {
        return new OperatorOracle();
    }
}
