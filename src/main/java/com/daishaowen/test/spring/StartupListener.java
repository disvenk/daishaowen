package com.daishaowen.test.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class StartupListener implements ApplicationContextAware,ServletContextListener{
    public static ApplicationContext application;
    public static ServletContext servletContext;
    Logger log = Logger.getLogger(getClass());
    @Override
    public void setApplicationContext(ApplicationContext context)throws BeansException {
        System.out.println("ddd");
        application = context;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ccc");
        servletContext = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("context was destoryed");
    }
}
