package com.daishaowen.test.exception;

import com.daishaowen.test.util.ErrorCode;
import com.daishaowen.test.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * [系统异常处理]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@ControllerAdvice
public class ExceptionActionAdvice {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 日志消息模板
	 */
	private final String errorMsgFormat = "Exception ===== [File][%s][Method][%s][Line][%d] ===== [%s].";

	/**
	 * [未知异常处理]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result exception(HttpServletRequest request, Exception e) {
		logException(e);
		return new Result().setError(ErrorCode.ERROR);
	}

//	/**
//	 * [框架异常处理]
//	 *
//	 * @author Chris li[黎超]
//	 * @version [版本, 2017-04-12]
//	 */
//	@ExceptionHandler(FrameworkException.class)
//	@ResponseBody
//	public Result frameworkException(HttpServletRequest request, Exception e) {
//		logException(e);
//		return new Result().setError(ErrorCode.FRAMEWORK);
//	}

//	/**
//	 * [断言失败处理]
//	 *
//	 * @author Chris li[黎超]
//	 * @version [版本, 2017-04-12]
//	 */
//	@ExceptionHandler(AssertFailedException.class)
//	@ResponseBody
//	public Result assertFailedException(HttpServletRequest request, Exception e) {
//		logException(e);
//		return new Result().setError(ErrorCode.ASSERT_FAILED, e.getMessage());
//	}
	
	/**
	 * [请求失败处理]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@ExceptionHandler(RequestFailedException.class)
	@ResponseBody
	public Result requestFailedException(HttpServletRequest request, Exception e) {
		logException(e);
		return new Result().setError(ErrorCode.REQUEST_FAILED, e.getMessage());
	}

	/**
	 * [用户未登陆异常处理]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@ExceptionHandler(NotLoggedInException.class)
	@ResponseBody
	public Result notLoggedException(HttpServletRequest request, Exception e) {
		logException(e);
		return new Result().setError(ErrorCode.NOT_LOGGED_IN, e.getMessage());
	}

	/**
	 * [登陆失败异常处理]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@ExceptionHandler(LoginFailedException.class)
	@ResponseBody
	public Result loginFailedException(HttpServletRequest request, Exception e) {
		logException(e);
		return new Result().setError(ErrorCode.LOGIN_FAILED, e.getMessage());
	}

//	/**
//	 * [Token失效异常处理]
//	 *
//	 * @author Chris li[黎超]
//	 * @version [版本, 2017-04-12]
//	 */
//	@ExceptionHandler(InvalidClaimException.class)
//	@ResponseBody
//	public Result tokenInvalidException(HttpServletRequest request, Exception e) {
//		logException(e);
//		return new Result().setError(ErrorCode.NOT_LOGGED_IN, e.getMessage());
//	}

	/**
	 * [自动登陆失败异常处理]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@ExceptionHandler(AutoLoginFailedException.class)
	@ResponseBody
	public Result autoLoginFailedException(HttpServletRequest request, Exception e) {
		logException(e);
		return new Result().setError(ErrorCode.AUTO_LOGIN_FAILED, e.getMessage());
	}

	/**
	 * [用户权限不足异常处理]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@ExceptionHandler(PermissionDeniedException.class)
	@ResponseBody
	public Result permissionDeniedException(HttpServletRequest request, Exception e) {
		logException(e);
		return new Result().setError(ErrorCode.UNAUTHORIZED);
	}

	/**
	 * [密码被修改了]
	 *
	 * @author disvenk[代绍文]
	 * @version [版本, 2017-04-12]
	 */
	@ExceptionHandler(UpdatePasswordException.class)
	@ResponseBody
	public Result updatePasswordException(HttpServletRequest request, Exception e) {
		logException(e);
		return new Result().setError(ErrorCode.UPDATED_PASSWORD);
	}

	/**
	 * [将异常信息记录到日志文件]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	private void logException(Exception e) {
		StackTraceElement element = e.getStackTrace()[0];
		String errorMsg = String.format(errorMsgFormat, element.getFileName(), element.getMethodName(), element.getLineNumber(), e
				.getLocalizedMessage());
		logger.error(errorMsg);
	}
}
