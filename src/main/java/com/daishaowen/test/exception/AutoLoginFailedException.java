package com.daishaowen.test.exception;

/**
 * [自动登录异常]
 *
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public class AutoLoginFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AutoLoginFailedException() {
        super();
    }

    public AutoLoginFailedException(String errorMsg) {
        super(errorMsg);
    }

    public AutoLoginFailedException(String errorMsg, Throwable t) {
        super(errorMsg, t);
    }

}
