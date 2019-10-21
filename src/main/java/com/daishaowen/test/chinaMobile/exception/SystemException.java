package com.daishaowen.test.chinaMobile.exception;

/**
 * @author wangchao
 *
 */
public class SystemException extends RuntimeException {

	/**
	 * serializable
	 */
	private static final long serialVersionUID = 1L;

	private ExceptionType exceptionType;

	public SystemException(final ExceptionType exceptionType) {

		this.exceptionType = exceptionType;
	}

	public ExceptionType getExceptionType() {

		return exceptionType;
	}

	public void setExceptionType(final ExceptionType exceptionType) {

		this.exceptionType = exceptionType;
	}

	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
