package com.daishaowen.test.chinaMobile.exception;

import org.apache.commons.lang3.Validate;

import java.text.MessageFormat;

/**
 * 异常类型枚举
 * @author wangchao
 *
 */
public enum SyncExceptionEnum {

    SYNC_SUCCESS(new ExceptionType("0", "同步成功")), // 成功
    SYNC_ERROR(new ExceptionType("1", "同步失败")); // 失败

    private ExceptionType exceptionType;

    SyncExceptionEnum(final ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

    /**
     * 设置exceptionMsg，返回一个新的ExceptionType实例
     * @param exceptionMsg
     * @return
     */
    public ExceptionType setExceptionMsg(final String exceptionMsg) {

        return new ExceptionType(exceptionType.getExceptionCode(), exceptionMsg);
    }

    /**
     * 设置格式化模板的exceptionMsg<br>
     * eg: 调用{0}系统的{1}接口失败
     * @param exceptionMsg 模板字符串
     * @param String数组 模板字符串入参
     */
    public ExceptionType setFormatMsg(final String exceptionMsg, final Object... obj) {

        Validate.noNullElements(obj);
        final String msg = MessageFormat.format(exceptionMsg, obj);
        return new ExceptionType(exceptionType.getExceptionCode(), msg);
    }

    public ExceptionType getExceptionType() {

        return exceptionType;
    }

    public void setExceptionType(final ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

}
