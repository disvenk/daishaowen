package com.daishaowen.test.chinaMobile.utils;

import java.util.Random;

/**
 * 生成验证码
 *
 * @author jiying
 * @date 2015年9月10日
 * @version 1.0
 */
public class VerifyCodeGenerator {

	/**
	 * 字符集
	 */
	private static char[] codeOption = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	private static Random random = new Random();

	/**
	 * 生成手机/邮箱验证码
	 * 
	 * @param codeCount
	 *            验证码数目
	 * @return
	 */
	public static String generateVerifyCode(final int codeCount) {

		final StringBuffer randomCode = new StringBuffer();
		for (int i = 0; i < codeCount; i++) {
			final String randomStr = String.valueOf(codeOption[random
					.nextInt(codeOption.length)]);
			randomCode.append(randomStr);
		}
		return randomCode.toString();
	}
}
