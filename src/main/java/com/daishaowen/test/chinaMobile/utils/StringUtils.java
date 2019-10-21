/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2017
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.daishaowen.test.chinaMobile.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * 判断是否是整数
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(final String str) {
		final Pattern pattern = Pattern.compile("^-?[0-9]+");
		final Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

}
