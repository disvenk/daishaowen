package com.daishaowen.test.shejimoshi.simpleFactoryModel;

import java.util.Scanner;

/**
 * Created by disvenk.dai on 2018-11-07 17:19
 * 下面的写法实现虽然简单，但是却没有面向对象的特性，代码拓展性差
 * 在面向对象编程语言中，一切都是对象，所以运算符号也应当作对象来处理。
 */
public class Calculate1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        float firstNum = in.nextFloat();
        System.out.println("请输入第二个数字：");
        float secondNum = in.nextFloat();
        System.out.println("请输入运算符号：");
        String countQuato = in.next();
        if ("+".equals(countQuato)) {
            System.out.println("result : " + (firstNum + secondNum));
        } else if ("-".equals(countQuato)) {
            System.out.println("result : " + (firstNum - secondNum));
        } else if ("*".equals(countQuato)) {
            System.out.println("result : " + (firstNum * secondNum));
        } else if ("/".equals(countQuato)) {
            System.out.println("result : " + (firstNum / secondNum));
        }
    }
}
