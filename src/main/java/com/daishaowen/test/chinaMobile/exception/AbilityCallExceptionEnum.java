/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2017
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.daishaowen.test.chinaMobile.exception;

public enum AbilityCallExceptionEnum {

	ABILITYCALL_SUCCESS("00000", "成功"),
//	ABILITYCALL_ABILITY_BASE("10006"),
	ABILITYCALL_ABILITY_BASE_31("10007", "流程节点未被成功调用"),
//	ABILITYCALL_ABILITY_BASE_30("10008", "当前流程包含的节点编码不能为空"),
	ABILITYCALL_ABILITY_BASE_29("10009","业务流程中不存在该节点"),
	ABILITYCALL_ABILITY_BASE_28("10010", "南向系统返回结果非正确的XML格式，执行数据脱敏策略失败"),
	ABILITYCALL_ABILITY_BASE_27("10011", "您输入错误次数过多，请5分钟后重试！"),
	ABILITYCALL_ABILITY_BASE_26("10012", "工号信息不存在"),
//	ABILITYCALL_ABILITY_BASE_25("10013", "该业务流程不存在"),
	ABILITYCALL_ABILITY_BASE_24("10014", "该业务流程版本号不能为空"),
	ABILITYCALL_ABILITY_BASE_23("10015", "该能力不存在或已下线"),
	ABILITYCALL_ABILITY_BASE_22("10016", "该流程异常结束"),
	ABILITYCALL_ABILITY_BASE_21("10017", "该业务流程已下线，本次调用失败"),
	ABILITYCALL_ABILITY_BASE_20("10018", "该业务流程已经调用失败，不能再使用该sessionId进行调用"),
	ABILITYCALL_ABILITY_BASE_19("10019", "该业务流程传入的节点编码对应的能力与urlPath不一致"),
	ABILITYCALL_ABILITY_BASE_18("10020", "该业务流程传入的节点编码无对应的执行节点信息"),
	ABILITYCALL_ABILITY_BASE_17("10021", "该业务流程节点非执行节点，非法调用"),
	ABILITYCALL_ABILITY_BASE_16("10022", "该业务流程节点已经被调用，不能重复调用"),
	ABILITYCALL_ABILITY_BASE_15("10023", "timestamp时间戳格式不正确"),
	ABILITYCALL_ABILITY_BASE_14("10024", "传入的能力已经被执行，不能重复调用"),
	ABILITYCALL_ABILITY_BASE_13("10025", "业务流程未关联到应用下，不能调用该业务流程"),
	ABILITYCALL_ABILITY_BASE_12("10026", "未找到该应用能力信息"),
	ABILITYCALL_ABILITY_BASE_11("10027", "该应用能力信息未设置回调地址"),
	ABILITYCALL_ABILITY_BASE_10("10028", "未找到可调用的执行节点，调用失败"),
	ABILITYCALL_ABILITY_BASE_9("10029", "短信模板获取异常，请联系管理员解决！"),
	ABILITYCALL_ABILITY_BASE_8("10030", "应用的AccessToken认证失败"),
	ABILITYCALL_ABILITY_BASE_7("10031", "本次访问来源在黑名单中,无法访问"),
	ABILITYCALL_ABILITY_BASE_6("10032", "未获取到来源ip"),
	ABILITYCALL_ABILITY_BASE_5("10033", "动态验证码校验失败"),
	ABILITYCALL_ABILITY_BASE_4("10034", "动态验证码不存在或已失效,请重新获取"),
	ABILITYCALL_ABILITY_BASE_3("10035", "动态验证码dynamicToken不能为空"),
	ABILITYCALL_ABILITY_BASE_2("10036", "该用户认证已超出失败重试次数,请重新生成"),
	ABILITYCALL_ABILITY_BASE_1("10037", "用户认证码不存在或已失效,请重新获取"),
	ABILITYCALL_PARM_ERROR("10038", "参数错误"),
	ABILITYCALL_PARM_ERROR_25("10039", "父节点编码错误"),
	ABILITYCALL_PARM_ERROR_24("10040", "父节点不能为空"),
	ABILITYCALL_PARM_ERROR_23("10041", "不存在ssb同步过来的该参数信息"),
	ABILITYCALL_PARM_ERROR_22("10042", "请求参数结构异常，请检查后重试"),
	ABILITYCALL_PARM_ERROR_21("10043", "应用与能力不匹配，请检查后重试"),
	ABILITYCALL_PARM_ERROR_20("10044", "认证方式错误，请检查后重试"),
	ABILITYCALL_PARM_ERROR_19("10045", "用户授权token获取方式配置未生效，请联系管理员重新设置！"),
//	ABILITYCALL_PARM_ERROR_18("10046", "用户授权token获取方式配置异常，请联系管理员重新设置！"),
	ABILITYCALL_PARM_ERROR_17("10047", "认证方式错误，请检查后重试"),
	ABILITYCALL_PARM_ERROR_16("10048", "请填写6位数值验证码"),
	ABILITYCALL_PARM_ERROR_15("10049", "手机号码格式错误"),
	ABILITYCALL_PARM_ERROR_14("10050", "sign为必填参数"),
	ABILITYCALL_PARM_ERROR_13("10051", "sessionId为必填参数"),
	ABILITYCALL_PARM_ERROR_12("10052", "messageId为必填参数"),
	ABILITYCALL_PARM_ERROR_11("10053", "accessToken为必填参数"),
	ABILITYCALL_PARM_ERROR_10("10054", "appId为必填参数"),
	ABILITYCALL_PARM_ERROR_9("10055", "timestamp为必填参数"),
	ABILITYCALL_PARM_ERROR_8("10056", "参数不合法"),
	ABILITYCALL_PARM_ERROR_7("10057", "不能选择父节点为root的参数"),
	ABILITYCALL_PARM_ERROR_6("10058", "返回参数不合法"),
//	ABILITYCALL_PARM_ERROR_5("10059", "业务流程未关联到应用下，不能调用该业务流程"),
	ABILITYCALL_PARM_ERROR_4("10060", "该流程不存在或不可用！"),
//	ABILITYCALL_PARM_ERROR_3("10061", "该业务流程节点编码不能为空"),
	ABILITYCALL_PARM_ERROR_2("10062", "该节点不存在或不可用！"),
//	ABILITYCALL_PARM_ERROR_1("10063", "该业务流程版本号不能为空"),
	ABILITYCALL_PARM_SWAGGER_ERROR("10064", "Swagger内容非json格式"),
	ABILITYCALL_PARM_PHONE_ERROR("10065", "userPhoneNumber:手机号码不合法"),
	ABILITYCALL_PARM_NULL("10066", "入参不能为空"),
	ABILITYCALL_PUBLICKEY_SIGN_ERRER("10067", "公钥解码sign值失败"),
	ABILITYCALL_CODE_ERROR("10068", "编码失败"),
	ABILITYCALL_PUBLICKEY_GET_ERRER("10069", "获取公钥失败"),
	ABILITYCALL_PUBLICKEY_MODE_ERRER("10070", "公钥格式不正确"),
	ABILITYCALL_PUBLICKEY_NULL("10071", "公钥字段publicKey未填写"),
	ABILITYCALL_SIGN_NULL("10072", "签名字段sign未填写"),
	ABILITYCALL_SIGN_NULL_CHECK("10073", "请检查签名参数是否为空"),
	ABILITYCALL_BEAN_COPY("10074", "bean转换异常"),
	ABILITYCALL_IPWHITE_FAIL("10075", "IP白名单认证失败"),
	ABILITYCALL_ABILITY_NOTSIGN("10076", "能力未签约"),
	ABILITYCALL_ABILITY_SUSPEND("10077", "能力处于暂停状态"),
	ABILITYCALL_APP_LOGOFF("10078", "应用不存在或已注销"),
//	ABILITYCALL_APP_LOGOFF_TIP("10079", "该应用不存在，请检查后重试"),
	ABILITYCALL_PARTNER_SUSPEND("10080", "合作伙伴无调用能力权限"),
	ABILITYCALL_PARTNER_LOGOFF("10081", "合作伙伴不存在或已注销"),
	ABILITYCALL_ABILITY_FLOWOVER("10082", "流控控制超过阀值，访问被拒绝"),
	ABILITYCALL_ABILITY_FLOWOVER_CURR_API("10083", "当前api流量控制超过阀值，访问被拒绝"),
	ABILITYCALL_ABILITY_QUOTAOVER("10084", "配额控制超过阀值，访问被拒绝"),
	ABILITYCALL_ABILITY_IDENTITY("10085", "用户身份认证错误，访问被拒绝"),
	ABILITYCALL_ABILITY_NOTPRD("10086", "应用未申请当前能力的生产环境访问权限，只能访问沙箱环境"),
	ABILITYCALL_ABILITY_SYSTEM("10087", "系统错误"),
	ABILITYCALL_HTTPMETHOD_NOT("10088", "Http Method错误"),
	ABILITYCALL_INVALID_SIGNATURE("10089", "请求数据被篡改,访问拒绝"),
//	ABILITYCALL_ABILITYINFO_NULL("10090", "未找到urlPath所对应的已上线API信息"),
	ABILITY_OUT_LINE("10091", "该能力不存在，请检查后重试"),
	AUTH_PASSWORD_NULL("10092", "用户授权信息不存在或已失效,请进行服务密码认证"),
	AUTH_PASSWORD_CANNOT_NULL("10093", "服务密码不能为空"),
	AUTH_SMSIDENT_NULL("10094", "用户授权信息不存在或已失效,请进行短信验证码认证"),
	AUTH_SMSIDENT_CANNOT_NULL("10095", "短信验证码不能为空"),
	AUTH_PASSWORD_AND_SMSIDENT_NULL("10096", "用户授权信息不存在或已失效,请进行服务密码和短信验证码认证"),
	AUTH_PASSWORD_AND_SMSIDENT_CANNOT_NULL("10097", "服务密码且验证码均不能为空"),
	AUTH_PASSWORD_OR_SMSIDENT_NULL("10098", "用户授权信息不存在或已失效,请进行服务密码或短信验证码认证"),
	AUTH_PASSWORD_OR_SMSIDENT_CANNOT_NULL("10099", "服务密码或验证码不能均为空"),
	AUTH_PASSWORD_ERROR("10100", "您输入的服务密码错误，请重新输入"),
	AUTH_PASSWORD_OR_SMSIDENT_ERROR("10101", "您输入的服务密码或验证码错误，请重新输入"),
	AUTH_PASSWORD_AND_SMSIDENT_ERROR("10102", "您输入的服务密码和短信验证码均错误，请重新输入"),
	AUTH_SMSIDENT_ERROR("10103", "您输入的短信验证码错误，请重新输入"),
	AUTH_TOKEN_ERROR("10104", "请求token不能为空"),
	ABILITYCALL_PROCESS_ORDER_ERROR("10105", "未按业务流程编排顺序调用能力API"),
	ABILITYCALL_PROCESS_ABILITY_NOT_EXIST("10106", "该能力API不在业务流程中"),
	ABILITYCALL_PROCESS_ID_NOT_EXIST("10107", "业务编码不存在"),
	ABILITYCALL_PARTNER_USERGROUP_NULL("10108", "合作伙伴所在的用户组不存在或已注销"),
	ABILITYCALL_PARTNER_USERGROUP_AUTH_NULL("10109", "合作伙伴所在的用户组授权信息不存在");

	private String code;
	private String value;

	private AbilityCallExceptionEnum(final String code, final String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	public static String getEnumValue(final String code) {
		for (final AbilityCallExceptionEnum dto : AbilityCallExceptionEnum
				.values()) {
			if (dto.getCode() == code) {
				return dto.getValue();
			}
		}
		return null;
	}

}
