package com.daishaowen.test.exception;

public class AssertFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AssertFailedException() {
        super();
    }

    public AssertFailedException(String errorMsg) {
        super(errorMsg);
    }

    public AssertFailedException(String errorMsg, Throwable t) {
        super(errorMsg, t);
    }

}
