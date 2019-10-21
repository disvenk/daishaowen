package com.daishaowen.test.chinaMobile.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseForJson {

    public enum DataType {
        JSON, ENCODEJSON
    }
    
    DataType dataType() default DataType.JSON;

}
