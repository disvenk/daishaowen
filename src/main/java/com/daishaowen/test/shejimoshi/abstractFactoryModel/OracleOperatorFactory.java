package com.daishaowen.test.shejimoshi.abstractFactoryModel;

/**
 * Created by disvenk.dai on 2018-11-09 10:38
 */
public class OracleOperatorFactory implements IFactory {
    @Override
    public UserOperator createUserOperator() {
        return new OracleOperatorUser();
    }

    @Override
    public LoginOperator createLoginOperator() {
        return new OracleOperatorLogin();
    }
}
