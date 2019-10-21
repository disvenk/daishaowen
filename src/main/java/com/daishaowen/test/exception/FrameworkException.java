package com.daishaowen.test.exception;

public class FrameworkException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FrameworkException() {
        super();
    }

    public FrameworkException(String errorMsg) {
        super(errorMsg);
    }

    public FrameworkException(String errorMsg, Throwable t) {
        super(errorMsg, t);
    }

}