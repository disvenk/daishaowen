package com.daishaowen.test.shejimoshi.simpleFactoryModel;

import java.util.Scanner;

/**
 * Created by disvenk.dai on 2018-11-07 17:19
 * 简单工厂将对象的创建过程进行了封装，用户不需要知道具体的创建过程，只需要调用工厂类获取对象即可。
 */
public class Calculate2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        float firstNum  = in.nextFloat();
        System.out.println("请输入第二个数字：");
        float secondNum  = in.nextFloat();
        System.out.println("请输入运算符号：");
        String countQuato = in.next();
        System.out.println(count(firstNum,secondNum,countQuato));
    }
    private static float count(float firstNum,float secondNum , String countQuota){
        //通过工厂类获取对象
        Operation operation = OperationFactory.getOperation(countQuota);
        return operation.getResult(firstNum,secondNum);
    }
}
