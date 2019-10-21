package com.daishaowen.test.duanyan;

import com.daishaowen.test.exception.AssertFailedException;
import com.daishaowen.test.exception.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public class Assert {
    /**
     * [正整数类型的分隔格式]
     */
    public final static String POSITIVE_INTEGER_TYPE_FORMAT = "^[1-9]\\d*(,[1-9]\\d*)*$";
    /**
     * [默认的日期格式]
     */
    public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * [默认的日期时间格式]
     */
    public final static String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * [断言表达式为真]
     */
    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true!");
    }

    /**
     * [断言表达式为真]
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言表达式为假]
     */
    public static void isFalse(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be false!");
    }

    /**
     * [断言表达式为假]
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言参数可转换成Long]
     */
    public static void isLong(String text) {
        isLong(text, "[Assertion failed] - this expression must be long!");
    }

    /**
     * [断言参数可转换成Long]
     */
    public static void isLong(String text, String message) {
        try {
            Long.valueOf(text);
        } catch (Exception e) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言参数不为空]
     */
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - the object argument must not be null!");
    }

    /**
     * [断言参数不为空]
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言参数为空或为空白字符串]
     */
    public static void isBlank(String text) {
        isBlank(text, "[Assertion failed] - this String argument must be null, empty, or blank!");
    }

    /**
     * [断言参数为空或为空白字符串]
     */
    public static void isBlank(String text, String message) {
        if (StringUtils.isNotBlank(text)) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言参数不为空,且不为空白字符串]
     */
    public static void notBlank(String text) {
        notBlank(text, "[Assertion failed] - this String argument must not be null, empty, or blank!");
    }

    /**
     * [断言参数不为空,且不为空白字符串]
     */
    public static void notBlank(String text, String message) {
        if (StringUtils.isBlank(text)) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言参数集合不为空]
     */
    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element!");
    }

    /**
     * [断言参数集合不为空]
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言参数集合不为空]
     */
    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry!");
    }

    /**
     * [断言参数集合不为空]
     *
     * @author Chris li[黎超]
     * @create [2017-04-12]
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言{clazz}被{annotationClass}注解标注]
     */
    public static void isAnnotationPresent(Class<? extends Annotation> annotationClass, Class<?> clazz) {
        isAnnotationPresent(annotationClass, clazz, String.format("[Assertion failed] - class[%s] must be annotation present by [%s]!", clazz
                .getName(), annotationClass.getName()));
    }

    /**
     * [断言{clazz}被{annotationClass}注解标注]
     */
    public static void isAnnotationPresent(Class<? extends Annotation> annotationClass, Class<?> clazz, String message) {
        notNull(annotationClass);
        notNull(clazz);
        if (!clazz.isAnnotationPresent(annotationClass)) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言{source}匹配Ids格式[id1,id2,...,idn],其中idn是正整数]
     */
    public static void isMatchedIdsFormat(String source) {
        isMatchedIdsFormat(source, String.format(
                "[Assertion failed] - [%s] is not matched ids format [id1,id2,id3,...,idn], which {idn} is positive integer!", source));
    }

    /**
     * [断言{source}匹配Ids格式[id1,id2,...,idn],其中idn是正整数]
     */
    public static void isMatchedIdsFormat(String source, String message) {
        notBlank(source);
        if (!Pattern.matches(POSITIVE_INTEGER_TYPE_FORMAT, source)) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言{text}是正确的日期格式[yyyy-MM-dd]]
     */
    public static void isDate(String text) {
        isDate(text, String.format(
                "[Assertion failed] - [%s] is not date[format = yyyy-MM-dd] string!", text));
    }

    /**
     * [断言{text}是正确的日期格式[yyyy-MM-dd]]
     */
    public static void isDate(String text, String message) {
        notBlank(text);
        try {
            isTrue(text.length() == DEFAULT_DATE_PATTERN.length());
            DateUtil.dateStrToDate(text, DEFAULT_DATE_PATTERN);
        } catch (Exception e) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言{text}是正确的日期时间格式[yyyy-MM-dd HH:mm:ss]]
     */
    public static void isDateTime(String text) {
        isDateTime(text, String.format(
                "[Assertion failed] - [%s] is not date[format = yyyy-MM-dd HH:mm:ss] string!", text));
    }

    /**
     * [断言{text}是正确的日期格式[yyyy-MM-dd HH:mm:ss]]
     */
    public static void isDateTime(String text, String message) {
        notBlank(text);
        try {
            isTrue(text.length() == DEFAULT_DATETIME_PATTERN.length());
            DateUtil.dateStrToDate(text, DEFAULT_DATETIME_PATTERN);
        } catch (Exception e) {
            throw new AssertFailedException(message);
        }
    }

    /**
     * [断言{text}是正确的日期格式{pattern}]
     */
    public static void isPatternDate(String text, String pattern) {
        isPatternDate(text, pattern, String.format(
                "[Assertion failed] - [%s] is not date[format = yyyy-MM-dd] string!", text, pattern));
    }

    /**
     * [断言{text}是正确的日期格式{pattern}]
     */
    public static void isPatternDate(String text, String pattren, String message) {
        notBlank(text);
        try {
            isTrue(text.length() == pattren.length());
            DateUtil.dateStrToDate(text, pattren);
        } catch (Exception e) {
            throw new AssertFailedException(message);
        }
    }
}
