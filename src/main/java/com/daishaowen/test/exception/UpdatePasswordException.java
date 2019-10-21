package com.daishaowen.test.exception;

public class UpdatePasswordException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UpdatePasswordException() {
        super();
    }

    public UpdatePasswordException(String errorMsg) {
        super(errorMsg);
    }

    public UpdatePasswordException(String errorMsg, Throwable t) {
        super(errorMsg, t);
    }
}
