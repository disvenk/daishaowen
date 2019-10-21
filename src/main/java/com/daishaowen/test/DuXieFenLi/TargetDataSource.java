package com.daishaowen.test.DuXieFenLi;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented //自动生成文档
public @interface TargetDataSource {
    String name();
}
