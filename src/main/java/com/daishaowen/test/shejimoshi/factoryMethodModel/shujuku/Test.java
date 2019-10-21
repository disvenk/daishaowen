package com.daishaowen.test.shejimoshi.factoryMethodModel.shujuku;

/**
 * Created by disvenk.dai on 2018-11-08 15:48
 */
public class Test {
    public static void main(String[] args) {
        //确定使用哪个数据库的工厂
        IFactory factory = new OperatorMysqlFactory();
        //生产出对应数据库的操作类
        Opereator operator = factory.getOperator();
        //执行操作
        User user = operator.getUser();
    }
}
