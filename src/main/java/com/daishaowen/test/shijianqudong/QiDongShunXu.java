package com.daishaowen.test.shijianqudong;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class QiDongShunXu implements ApplicationContextAware, ApplicationListener, InitializingBean, ServletContextListener {

    private static  int order =0;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware是第"+(++order)+"个启动");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("ApplicationListener是第"+(++order)+"个启动");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean是第"+(++order)+"个启动");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener是第"+(++order)+"个启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
