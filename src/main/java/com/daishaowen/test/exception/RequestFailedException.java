package com.daishaowen.test.exception;

/**
 * [请求失败异常]
 *
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public class RequestFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RequestFailedException(String errorMsg) {
        super(errorMsg);
    }

    public RequestFailedException(String errorMsg, Throwable t) {
        super(errorMsg, t);
    }

}
