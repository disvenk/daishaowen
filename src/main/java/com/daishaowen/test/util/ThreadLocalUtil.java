package com.zx.soft.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal工具类，可以动态创建ThreadLocal对象
 * 
 * @author wangxj
 */

@SuppressWarnings("unchecked")
public class ThreadLocalUtil {

	private static Map<String, ThreadLocal> tlMap = new HashMap<String, ThreadLocal>();

	/**
	 * 获取key为region的ThreadLocal对象，如果不存在，会新创建一个；valueType为ThreadLocal的值类型
	 * 
	 * @param region
	 * @param valueType
	 * @return
	 */
	public static <T> ThreadLocal<T> getRegion(String region, Class<T> valueType) {
		ThreadLocal<T> tl = tlMap.get(region);
		if (tl == null) {
			tl = addRegion(region, valueType);
		}
		return tl;
	}

	/**
	 * 创建key为region的ThreadLocal对象，valueType为ThreadLocal的值类型
	 * 
	 * @param region
	 * @param valueType
	 * @return
	 */
	private static synchronized <T> ThreadLocal<T> addRegion(String region, Class<T> valueType) {
		ThreadLocal<T> tl = tlMap.get(region);
		if (tl == null) {
			tl = new ThreadLocal();
			tlMap.put(region, tl);
		}
		return tl;
	}

}
