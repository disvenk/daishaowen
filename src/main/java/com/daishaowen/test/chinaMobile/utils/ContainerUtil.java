package com.daishaowen.test.chinaMobile.utils;

import java.util.List;

/**
 * container util
 *
 * @author guolijuan
 *
 */
public class ContainerUtil {

	public static boolean isEmpty(final Object[] arr) {

		return arr == null || arr.length == 0;

	}

	public static boolean isNotEmpty(final Object[] arr) {
		return !isEmpty(arr);
	}

	public static <T> boolean isEmpty(final List<T> list) {
		return list == null || list.size() == 0;
	}

	public static <T> boolean isNotEmpty(final List<T> list) {
		return !isEmpty(list);
	}
}
