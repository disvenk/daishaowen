package com.daishaowen.test.util;

import com.daishaowen.test.chinaMobile.exception.AppException;
import com.daishaowen.test.chinaMobile.exception.ExceptionEnum;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by disvenk.dai on 2018-09-11 15:59
 */
public class ValidationUtils {
    /**
     * 使用hibernate的注解来进行验证
     *
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new AppException(ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg(String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage())));
        }
    }
}
