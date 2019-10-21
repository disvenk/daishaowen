package com.daishaowen.test.shejimoshi.simpleFactoryModel;

/**
 * Created by disvenk.dai on 2018-11-07 17:45
 * //接下来需要解决的就是对象的创建问题了，既如何根据不同的情况创建不同的对象：我们正好可以通过简单工厂模式实现
 */
public class OperationFactory {
    public static Operation getOperation(String quotaFlag){
        Operation o = null;
        switch (quotaFlag){
            case "+" :  o = new AddOperation();
            case "-" :  o = new SubOperation();
            case "*" :  o = new MulOperation();
            case "/" :  o = new DivOperation();
            default:break;
        }
        return o;
    }
}
