package com.daishaowen.test.chinaMobile.constants;

public class BasePatterns {

	/**
	 * 邮箱格式
	 */
	public final static String EMAIL_FORMAT = "^[a-z0-9A-Z]+([-|_|\\.]+[a-z0-9A-Z]+)*@([a-z0-9A-Z]+[-|\\.])+[a-zA-Z]{2,5}$"; //$NON-NLS-1$

	/**
	 * 手机格式
	 */
	public final static String PHONE_FORMAT = "^1[34578]\\d{9}$"; //$NON-NLS-1$

	/**
	 * 密码格式
	 */
	public final static String PASSWORD_FORMAT = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$"; //$NON-NLS-1$

	/**
	 * 新的密码格式
	 */
	public final static String NEW_PASSWORD_FORMAT = "^(?![0-9a-z]+$)(?![0-9A-Z]+$)(?![0-9\\W_]+$)(?![a-z\\W_]+$)(?![a-zA-Z]+$)(?![A-Z\\W_]+$)[a-zA-Z0-9\\W_]{6,15}+$"; //$NON-NLS-1$

	/**
	 * 身份证号码格式
	 */
	public final static String ID_NUMBER_FORMAT = "^(\\d{15}|\\d{18}|\\d{17}(\\d|X|x))$"; //$NON-NLS-1$

	/**
	 * 非空格式
	 */
	public final static String NOT_SPACE_FORMAT = "^[\\s\\S]*[^\\s]+[\\s\\S]*$"; //$NON-NLS-1$

	/**
	 * 营业执照格式， 13、15、18、20、22、24位
	 */
	public final static String BUSINESS_LICENSE_FORMAT = "^[0-9A-Za-z]{13}|[0-9A-Za-z]{15}|[0-9A-Za-z]{18}|[0-9A-Za-z]{20}|[0-9A-Za-z]{22}|[0-9A-Za-z]{24}$"; //$NON-NLS-1$

	/**
	 * 组织机构代码证格式，10位，其规则为“XXXXXXXX-X”，倒数第2位是“-”
	 */
	public final static String ORGANIZATION_CODE_FORMAT = "^[0-9A-Za-z]{8}-[0-9A-Za-z]{1}$"; //$NON-NLS-1$

	/**
	 * 事业单位法人登记证书格式，12位
	 */
	public final static String IIPRC_FORMAT = "^[0-9A-Za-z]{12}$"; //$NON-NLS-1$

	/**
	 * 社会团体法人登记证书：证件号码长度满足9位，或者10位“XXXXXXXX-X”，倒数第2位是“-”
	 */
	public final static String SORC_FORMAT = "^[0-9A-Za-z]{9}|[0-9A-Za-z]{8}-[0-9A-Za-z]{1}"; //$NON-NLS-1$

	/**
	 * 非法字符
	 */
	public final static String ILLEGAL_STR = "[^?!￥@#$%\\^&*()]+"; //$NON-NLS-1$

	/**
	 * IP地址格式
	 */
	public final static String IP_FORMAT = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$"; //$NON-NLS-1$

	/**
	 * 长度50限制
	 */
	public final static String LENGTH_FIFTY = "^\\S{1,50}$";

	/**
	 * 业务流程编码格式,长度20限制
	 */
	public final static String BIPCODE_FORMAT = "^[0-9A-Za-z_]{1,20}$";

	/**
	 * 长度2000限制
	 */
	public final static String LENGTH_TWO_THOUSAND = "^\\S{1,2000}$";

	/**
	 * 受理地市（regioncode）为字母/数字组合(只是数字或者字母、或者数字与字母组合)
	 */
	public final static String REGIONCODE_FORMAT = "^[0-9a-zA-Z]+$";

	/**
	 * 受理县区（countrycode）为字母/数字组合(只是数字或者字母、或者数字与字母组合)
	 */
	public final static String COUNTRYCODE__FORMAT = "^[0-9a-zA-Z]+$";

	/**
	 * 受理营业厅（orgid）为字母/数字组合(只是数字或者字母、或者数字与字母组合)
	 */
	public final static String ORGID_FORMAT = "^[0-9a-zA-Z]+$";

	/**
	 * URL
	 */
	public final static String URL_FORMAT = "^((http|https):\\/\\/)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})*(\\/[a-zA-Z0-9\\&%_\\.\\/-~-;?@:=+$,!'\\(\\)<>#%\"]*)?$";

	// 9位数字
	public final static String POSITIMVE_INTEGER = "^[1-9]\\d{0,8}$";

	// 正整数
	public final static String POSITIMVE_INTEGER2 = "^[0-9]*[1-9][0-9]*$";

	/**
     * 防SQL注入
     */
    public final static String ANTI_SQL_INJECTION = "(\\s*'\\s*)|('\\s*--\\s)|('\\s*(and|or|xor|&&|\\|\\|)\\s*\\(?\\s*('|[0-9]|`?[a-z\\._-]+`?\\s*(=|like)|[a-z]+\\s*\\())|('\\s*(not\\s+)?in\\s*\\(\\s*['0-9])|(union(\\s+all)?(\\s*\\(\\s*|\\s+)select(`|\\s))|(select(\\s*`|\\s+)(\\*|[a-z0-9_\\, ]*)(`\\s*|\\s+)from(\\s*`|\\s+)[a-z0-9_\\.]*)|(insert\\s+into(\\s*`|\\s+).*(`\\s*|\\s+)(values\\s*)?\\(.*\\))|(update(\\s*`|\\s+)[a-z0-9_\\.]*(`\\s*|\\s+)set(\\s*`|\\s+).*=)|(delete\\s+from(\\s*`|\\s+)[a-z0-9_\\.]*`?)";

    /**
     * 匹配数字
     */
    public final static String NUMBER = "-?(\\d*|[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)";
}
