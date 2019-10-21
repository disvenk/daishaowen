package com.daishaowen.test.chinaMobile.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author luyao
 *
 *         2015年6月30日
 */
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	// 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	@Override
	public void setApplicationContext(
			final ApplicationContext applicationContext) {

		SpringContextHolder.applicationContext = applicationContext;
	}

	// 取得存储在静态变量中的ApplicationContext.
	public static ApplicationContext getApplicationContext() {

		checkApplicationContext();
		return applicationContext;
	}

	// 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	public static <T> T getBean(final String name, final Class<T> T) {

		checkApplicationContext();
		return applicationContext.getBean(name, T);
	}

	// 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	public static Object getBean(final String name) {

		checkApplicationContext();
		return applicationContext.getBean(name);
	}

	// 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	// 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	// 如果有多个Bean符合Class, 取出第一个.
	@SuppressWarnings("unchecked")
	public static <T> T getBean(final Class<T> clazz) {

		checkApplicationContext();
		@SuppressWarnings("rawtypes")
		final Map beanMaps = applicationContext.getBeansOfType(clazz);
		if (beanMaps != null && !beanMaps.isEmpty()) {
			return (T) beanMaps.values().iterator().next();
		} else {
			return null;
		}
	}

	private static void checkApplicationContext() {

		if (applicationContext == null) {
			throw new IllegalStateException(
					"applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}

	/**
	 * 获取当前request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {

		final RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes != null) {
			return ((ServletRequestAttributes) requestAttributes).getRequest();
		} else {
			return null;
		}
	}
}