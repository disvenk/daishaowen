package com.daishaowen.test.validator;

/**
 * Created by disvenk.dai on 2018-10-20 16:19
 */

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//element.type 注解用于类
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy=BaseVal.ValidatorTest.class)
//@Repeatable(LinkVals.class)(可重复注解同一字段，或者类，java1.8后支持）

public @interface BaseVal {

    class ValidatorTest implements ConstraintValidator<BaseVal, Object> {

        @Override
        public void initialize(BaseVal constraintAnnotation) {

        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

            return false;
        }
    }
}
