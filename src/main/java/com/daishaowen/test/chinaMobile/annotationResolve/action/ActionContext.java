package com.daishaowen.test.chinaMobile.annotationResolve.action;

import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * action上下文
 * 
 * @author wangchao
 *
 */
public class ActionContext {

	HttpServletRequest request;

	HandlerMethod handlerMethod;

	public ActionContext(final HttpServletRequest request,
                         final HandlerMethod handlerMethod) {

		this.request = request;
		this.handlerMethod = handlerMethod;
	}

	public HandlerMethod getHandlerMethod() {

		return handlerMethod;
	}

	public void setHandlerMethod(final HandlerMethod handlerMethod) {

		this.handlerMethod = handlerMethod;
	}

	public HttpServletRequest getRequest() {

		return request;
	}

	public void setRequest(final HttpServletRequest request) {

		this.request = request;
	}

}
