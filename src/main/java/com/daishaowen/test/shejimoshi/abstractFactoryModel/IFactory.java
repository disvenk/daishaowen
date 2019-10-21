package com.daishaowen.test.shejimoshi.abstractFactoryModel;

/**
 * Created by disvenk.dai on 2018-11-09 10:36
 */
public interface IFactory {
    public UserOperator createUserOperator();
    public LoginOperator createLoginOperator();
}
