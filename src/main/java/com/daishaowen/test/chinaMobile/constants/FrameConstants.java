package com.daishaowen.test.chinaMobile.constants;

/**
 *
 * @author wangchao
 *
 */
public class FrameConstants {

	/**
	 * 成功标志
	 */
	public static final String SUCCESS = "SUCCESS";

	/**
	 * 服务器异常
	 */
	public static final String SERVER_ERROR = "SERVER_ERROR";

	/**
	 * request id key
	 */
	public static final String CALL_ID = "callId";

	/**
	 * ABILITYCALL_ID request id key
	 */
	public static final String ABILITYCALL_ID = "abilityCallId";

	/**
	 * 用户Id
	 */
	public static final String USER_ID = "userId";
	
	public static final String TENANT_ID = "tenantId";

	/**
	 * 用户名称
	 */
	public static final String USER_NAME = "username";

	/**
	 * 运营人员名称
	 */
	public static final String OPERATOR_NAME = "operatorName";

	/**
	 * 用户状态
	 */
	public static final String USER_STATUS = "userStatus";

	/**
	 * 用户类型
	 */
	public static final String USER_TYPE = "userType";

	/**
	 * 个人用户登录类型
	 */
	public static final Integer PERSONAL_LOGIN_TYPE = 1;

	/**
	 * 企业用户登录类型
	 */
	public static final Integer COMPANY_LOGIN_TYPE = 2;

	/**
	 * 运营人员登录类型
	 */
	public static final Integer OPERATOR_LOGIN_TYPE = 3;

	/**
	 * 能力调用发起时间
	 */
	public static final String ABILITY_START_TIME = "abilityCallStartTime";

	/**
	 * 能力id
	 */
	public static final String ABILITY_ID = "abilityId";

	/**
	 * 能力入参
	 */
	public static final String ABILITY_PARAM = "abilityParm";

	/**
	 * 能力调用结果成功
	 */
	public static final String ABILITY_SUCCESS = "成功";

	/**
	 * 能力调用结果失败
	 */
	public static final String ABILITY_FAIL = "失败";

	/**
	 * 能力调用结果未知
	 */
	public static final String ABILITY_OTHER = "其它";

	/**
	 * redis中合作伙伴key的前缀
	 */
	public static final String PSID = "PSID";

	/**
	 * redis中运营商key的前缀
	 */
	public static final String OSID = "OSID";

	/**
	 * redis中存放session key的分隔符
	 */
	public static final String SEPARATOR = ",";

}
