package com.daishaowen.test.chinaMobile.annotation;

import java.lang.annotation.*;

/**
 * To support Authentication verify
 * @author yuanjian
 *
 */

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAuthentication {

}
