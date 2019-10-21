package com.daishaowen.test.aop.chuantongaop;

import com.daishaowen.test.shejimoshi.templateModel.cookie.App;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("chuantongaop.xml");
        IorderService orderServicePoxy = (IorderService)ApplicationContext.getBean("orderServicePoxy");
        orderServicePoxy.add();
    }
}
