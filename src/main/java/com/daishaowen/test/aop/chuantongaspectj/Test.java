package com.daishaowen.test.aop.chuantongaspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("chuantongaspectj.xml");
        HelloWord hw = (HelloWord)ctx.getBean("helloWorldImpl");

        hw.printHelloWord();
        hw.doPrint();
    }
}
