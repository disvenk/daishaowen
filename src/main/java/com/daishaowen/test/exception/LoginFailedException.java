package com.daishaowen.test.exception;

/**
 * [用户登录异常]
 *
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public class LoginFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String errorMsg) {
        super(errorMsg);
    }

    public LoginFailedException(String errorMsg, Throwable t) {
        super(errorMsg, t);
    }

}
