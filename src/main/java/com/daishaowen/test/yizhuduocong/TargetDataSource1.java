package com.daishaowen.test.yizhuduocong;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented //自动生成文档
public @interface TargetDataSource1 {
    String name();
}
