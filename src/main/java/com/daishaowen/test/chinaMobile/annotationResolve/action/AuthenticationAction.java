package com.daishaowen.test.chinaMobile.annotationResolve.action;

import com.daishaowen.test.chinaMobile.annotation.NoAuthentication;
import com.daishaowen.test.chinaMobile.constants.FrameConstants;
import com.daishaowen.test.chinaMobile.exception.ExceptionEnum;
import com.daishaowen.test.chinaMobile.exception.NeedLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * To verify authentication
 *
 * @author yuanjian
 *
 */
public class AuthenticationAction implements IWorkAction {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationAction.class);

	@Override
	public boolean isMatch(final ActionContext context) {

		final NoAuthentication noauthority = context.getHandlerMethod()
				.getMethodAnnotation(NoAuthentication.class);
		return noauthority == null;
	}

	@Override
	public boolean doAction(final ActionContext context) throws Exception {// NOPMD

		LOGGER.debug("Client " + context.getRequest().getLocalAddr()
				+ " try to access " + context.getRequest().getRequestURL());
		final HttpSession session = context.getRequest().getSession(false);
		if (null == session
				|| null == session.getAttribute(FrameConstants.USER_ID)) {// 该用户没有登录
			throw new NeedLoginException(
					ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg("请登录！"));
		}

		return true;
	}

	@Override
	public void after(final ActionContext context) {

		// nothing to do
	}

}
