package com.daishaowen.test.chinaMobile.exception;

/**
 * 需要登录异常 - 属于业务异常<br>
 * 
 * @author yuanjian
 *
 */
public class NeedLoginException extends BusinessException {

	public NeedLoginException(final ExceptionType exceptionType) {
		super(exceptionType);
	}

	private static final long serialVersionUID = -1217280798001238598L;

}
