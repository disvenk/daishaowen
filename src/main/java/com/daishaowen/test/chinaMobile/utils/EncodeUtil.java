package com.daishaowen.test.chinaMobile.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * 加密工具类
 *
 * @author wangchao
 *
 */
public class EncodeUtil {

	public static final String ENCODING = "UTF-8"; //$NON-NLS-1$

	/**
	 * 使用URL编码
	 *
	 * @param inputStr
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeInURL(final String inputStr) throws UnsupportedEncodingException {

		return URLEncoder.encode(inputStr, ENCODING);
	}

	/**
	 * 使用MD5编码成32位字符串
	 *
	 * @param inputStr
	 * @return String
	 */
	public static String encodeInMD5(final String inputStr) {
		if (StringUtils.isEmpty(inputStr)) {
			return inputStr;
		}
		return DigestUtils.md5DigestAsHex(inputStr.getBytes());
	}

}
