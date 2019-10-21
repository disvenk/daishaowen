package com.daishaowen.test.yizhuduocong;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1) //优先执行
@Component
@Slf4j
public class DynamicDataSourceAspect1 {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint joinPoint,TargetDataSource1 ds){
        String dsId = ds.name();
        DatabaseContextHolder1.setDataSourceName(dsId);
    }
}
