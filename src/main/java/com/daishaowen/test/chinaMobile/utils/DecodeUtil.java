package com.daishaowen.test.chinaMobile.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 解码工具类
 *
 * @author wangchao
 *
 */
public class DecodeUtil {

	public static final String ENCODING = "UTF-8";

	/**
	 * 使用URL解码
	 *
	 * @param inputStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeInURL(final String inputStr)
			throws UnsupportedEncodingException {

		return URLDecoder.decode(inputStr, ENCODING);
	}
}
