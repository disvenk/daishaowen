package com.daishaowen.test.shejimoshi.abstractFactoryModel;

/**
 * Created by disvenk.dai on 2018-11-09 10:41
 */
public class Test {
    public static void main(String[] args) {
        //确定数据库工厂
        IFactory factory = new MysqlOperatorFactroy();
        //生产出操作类
        UserOperator userOperator = factory.createUserOperator();
        //执行操作
        userOperator.insert();
        userOperator.getUser();
        LoginOperator loginOperator = factory.createLoginOperator();
        loginOperator.insert();
        loginOperator.getLogin();
    }
}
