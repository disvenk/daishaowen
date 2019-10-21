package com.daishaowen.test.devTest;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SpringTest {
    @PostConstruct
    public void init(){
        System.out.println("初始化方法");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("类被销毁");
    }
}
