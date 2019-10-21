package com.daishaowen.test.duanyan;

import com.daishaowen.test.util.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * [断言工具类]
 *
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public class MyAssert extends Assert {

    /**
     * [断言表达式为真]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isTrue(boolean expression, ErrorCode errorCode) {
        isTrue(expression, errorCode, errorCode.getDesc());
    }

    /**
     * [断言表达式为真]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isTrue(boolean expression, ErrorCode errorCode, String message) {
        if (!expression) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言表达式为假]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isFalse(boolean expression, ErrorCode errorCode) {
        isFalse(expression, errorCode, errorCode.getDesc());
    }

    /**
     * [断言表达式为假]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isFalse(boolean expression, ErrorCode errorCode, String message) {
        if (expression) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言参数可转换成Long]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isLong(String text, ErrorCode errorCode) {
        isLong(text, errorCode, errorCode.getDesc());
    }

    /**
     * [断言参数可转换成Long]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isLong(String text, ErrorCode errorCode, String message) {
        try {
            Long.valueOf(text);
        } catch (Exception e) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言参数不为空]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notNull(Object object, ErrorCode errorCode) {
        notNull(object, errorCode, errorCode.getDesc());
    }

    /**
     * [断言参数不为空]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notNull(Object object, ErrorCode errorCode, String message) {
        if (object == null) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言参数为空或为空白字符串]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isBlank(String text, ErrorCode errorCode) {
        isBlank(text, errorCode, errorCode.getDesc());
    }

    /**
     * [断言参数为空或为空白字符串]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isBlank(String text, ErrorCode errorCode, String message) {
        if (StringUtils.isNotBlank(text)) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言参数不为空,且不为空白字符串]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notBlank(String text, ErrorCode errorCode) {
        notBlank(text, errorCode, errorCode.getDesc());
    }

    /**
     * [断言参数不为空,且不为空白字符串]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notBlank(String text, ErrorCode errorCode, String message) {
        if (StringUtils.isBlank(text)) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言参数集合不为空]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notEmpty(Collection<?> collection, ErrorCode errorCode) {
        notEmpty(collection, errorCode, errorCode.getDesc());
    }

    /**
     * [断言参数集合不为空]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notEmpty(Collection<?> collection, ErrorCode errorCode, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言参数集合不为空]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notEmpty(Map<?, ?> map, ErrorCode errorCode) {
        notEmpty(map, errorCode, errorCode.getDesc());
    }

    /**
     * [断言参数集合不为空]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void notEmpty(Map<?, ?> map, ErrorCode errorCode, String message) {
        if (map == null || map.isEmpty()) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言{clazz}被{annotationClass}注解标注]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isAnnotationPresent(Class<? extends Annotation> annotationClass, Class<?> clazz, ErrorCode errorCode) {
        isAnnotationPresent(annotationClass, clazz, errorCode, errorCode.getDesc());
    }

    /**
     * [断言{clazz}被{annotationClass}注解标注]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isAnnotationPresent(Class<? extends Annotation> annotationClass, Class<?> clazz, ErrorCode errorCode, String message) {
        notNull(annotationClass);
        notNull(clazz);
        if (!clazz.isAnnotationPresent(annotationClass)) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [断言{source}匹配Ids格式[id1,id2,...,idn],其中idn是正整数]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isMatchedIdsFormat(String source, ErrorCode errorCode) {
        isMatchedIdsFormat(source, errorCode, errorCode.getDesc());
    }

    /**
     * [断言{source}匹配Ids格式[id1,id2,...,idn],其中idn是正整数]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void isMatchedIdsFormat(String source, ErrorCode errorCode, String message) {
        notBlank(source);
        if (!Pattern.matches(POSITIVE_INTEGER_TYPE_FORMAT, source)) {
            throw buildException(errorCode, message);
        }
    }

    /**
     * [生成异常对象]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    private static RuntimeException buildException(ErrorCode errorCode, String message) {
        try {
            return (RuntimeException) errorCode.getExceptionClazz().getConstructor(String.class).newInstance(message);
        } catch (Exception e) {
            return new RuntimeException("未知异常:" + e.getMessage());
        }
    }

}
