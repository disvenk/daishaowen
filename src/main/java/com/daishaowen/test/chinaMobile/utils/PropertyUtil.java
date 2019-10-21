/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * CMSS Co.,Ltd. The programs may be used and/or copied only with written
 * permission from CMSS Co.,Ltd. or in accordance with the terms and conditions
 * stipulated in the agreement/contract under which the program(s) have been
 * supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.daishaowen.test.chinaMobile.utils;

import java.util.ResourceBundle;

public class PropertyUtil {
	private static ResourceBundle bundle = null;

	public PropertyUtil(final String path) {
		bundle = ResourceBundle.getBundle(path);
	}

	public static String get(final String key) {
		String result = ""; //$NON-NLS-1$
		try {
			result = bundle.getString(key);
		} catch (final Exception e) {
			return result;
		}
		return result;
	}

}
