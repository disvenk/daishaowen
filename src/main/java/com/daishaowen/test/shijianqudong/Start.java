package com.daishaowen.test.shijianqudong;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by disvenk.dai on 2018-12-03 14:51
 */

//凡是继承该接口的类,在初始化bean的时候都会执行该方法
@Component
public class Start implements InitializingBean {

    @PostConstruct//此方法在前执行
    public void init(){
        System.out.println("后执行init方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("所有的类初始化完毕");
    }
}
