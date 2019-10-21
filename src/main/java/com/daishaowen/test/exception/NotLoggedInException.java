package com.daishaowen.test.exception;

/**
 * [未登录异常,即用户未登录,或登录超时]
 *
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public class NotLoggedInException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotLoggedInException() {
        super();
    }

    public NotLoggedInException(String errorMsg) {
        super(errorMsg);
    }

    public NotLoggedInException(String errorMsg, Throwable t) {
        super(errorMsg, t);
    }

}
