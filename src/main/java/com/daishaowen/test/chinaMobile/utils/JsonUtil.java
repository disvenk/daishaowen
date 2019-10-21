package com.daishaowen.test.chinaMobile.utils;


import com.daishaowen.test.chinaMobile.exception.ExceptionEnum;
import com.daishaowen.test.chinaMobile.exception.SystemException;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * 将json字符串转为目标对象
	 *
	 * @param json
	 *            待转换字符串
	 * @param obj
	 *            目标对象class
	 * @return 目标对象实例
	 */
	public static <T> T toBean(final String json, final Class<T> obj) {

		try {
			return JsonFormatter.toObject(json, obj);
		} catch (final Exception e) {
			LOGGER.error("JsonUtil.toBean error: ", e); //$NON-NLS-1$
			throw new SystemException(ExceptionEnum.EXECUTE_RUNTIME_EXCP.setExceptionMsg(e.getLocalizedMessage()));
		}
	}

	/**
	 * 将目标对象转为json字符串
	 *
	 * @param obj
	 *            目标对象
	 * @return String json串
	 */
	public static String toString(final Object obj) {

		try {
			return JsonFormatter.toString(obj);
		} catch (final Exception e) {
			LOGGER.error("JsonUtil.toString error: ", e); //$NON-NLS-1$
			throw new SystemException(ExceptionEnum.EXECUTE_RUNTIME_EXCP.setExceptionMsg(e.getLocalizedMessage()));
		}
	}

	/**
	 * 将json字符串转为list
	 *
	 * @param jsonStr
	 *            json字符串
	 * @param clazz
	 *            目标对象class
	 * @return
	 */
	public static <T> List<T> toList(final String jsonStr, final Class<T> clazz) {

		try {
			return JsonFormatter.toList(jsonStr, clazz);
		} catch (final Exception e) {
			LOGGER.error("JsonUtil.toList error: ", e); //$NON-NLS-1$
			throw new SystemException(ExceptionEnum.EXECUTE_RUNTIME_EXCP.setExceptionMsg(e.getLocalizedMessage()));
		}

	}

	/**
	 * 将字符串转换成JsonNode
	 *
	 * @param jsonStr
	 *            json字符串
	 * @return
	 */
	public static JsonNode toJsonNode(final String jsonStr) {

		try {
			return JsonFormatter.toJsonNode(jsonStr);
		} catch (final Exception e) {
			LOGGER.error("JsonUtil.toJsonNode error: ", e); //$NON-NLS-1$
			throw new SystemException(ExceptionEnum.EXECUTE_RUNTIME_EXCP.setExceptionMsg(e.getLocalizedMessage()));
		}
	}

}
