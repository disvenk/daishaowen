package com.daishaowen.test.shejimoshi.factoryMethodModel.shujuku;

/**
 * Created by disvenk.dai on 2018-11-08 15:46
 */
public class OperatorMysqlFactory implements IFactory {
    @Override
    public Opereator getOperator() {
        return new OperatorMysql();
    }
}
