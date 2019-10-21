package com.daishaowen.test.shejimoshi.factoryMethodModel.jiajianchengchu;

import com.daishaowen.test.shejimoshi.simpleFactoryModel.Operation;

import java.util.Scanner;

/**
 * Created by disvenk.dai on 2018-11-07 17:53
 */
public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        float firstNum  = in.nextFloat();
        System.out.println("请输入第二个数字：");
        float secondNum  = in.nextFloat();
        IFactory fractory = new AddOperationFactory();
        Operation operation = fractory.generateOper();
        System.out.println(operation.getResult(firstNum,secondNum));
    }
}
