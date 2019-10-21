package com.daishaowen.test.DuXieFenLi;

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
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint joinPoint,TargetDataSource ds){
        String dsId = ds.name();
        DatabaseContextHolder.setDataSourceName(dsId);
    }
}
