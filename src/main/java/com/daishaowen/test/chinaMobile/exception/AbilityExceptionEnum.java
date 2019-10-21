package com.daishaowen.test.chinaMobile.exception;

import org.apache.commons.lang3.Validate;

import java.text.MessageFormat;

/**
 * 能力调用异常枚举
 *
 * @author wangchao
 *
 */
public enum AbilityExceptionEnum {

    ABILITYCALL_SUCCESS(new ExceptionType("A0000", "成功")), ABILITYCALL_IPWHITE_FAIL(new ExceptionType("A0101",
            "IP白名单认证失败")), ABILITYCALL_TOKEN_FAIL(new ExceptionType("A0102", "AccessToken认证失败")), ABILITYCALL_PUBLISH_PRD(
            new ExceptionType("A0103", "应用未发布，无调用生产环境权限")), ABILITYCALL_PUBLISH_BOX(new ExceptionType("A0104",
            "应用已发布，无调用沙箱环境权限")), ABILITYCALL_ABILITY_NOTSIGN(new ExceptionType("A0201", "能力未签约")), ABILITYCALL_ABILITY_SUSPEND(
            new ExceptionType("A0202", "能力处于暂停状态")), ABILITYCALL_APP_LOGOFF(new ExceptionType("A0203", "应用不存在或已注销")), ABILITYCALL_PARTNER_SUSPEND(
            new ExceptionType("A0204", "合作伙伴无调用能力权限")), ABILITYCALL_PARTNER_LOGOFF(new ExceptionType("A0205",
            "合作伙伴不存在或已注销")), ABILITYCALL_ABILITY_FLOWOVER(new ExceptionType("A0301", "流控控制超过阀值，访问被拒绝")), ABILITYCALL_ABILITY_QUOTAOVER(
            new ExceptionType("A0401", "配额控制超过阀值，访问被拒绝")), ABILITYCALL_ABILITY_IDENTITY(new ExceptionType("A0501",
            "用户身份认证错误，访问被拒绝")), ABILITYCALL_ABILITY_NOTPRD(new ExceptionType("A0601", "应用未申请当前能力的生产环境访问权限，只能访问沙箱环境")), ABILITYCALL_ABILITY_SYSTEM(
            new ExceptionType("A0901", "系统错误")), ABILITYCALL_HTTPMETHOD_NOT(new ExceptionType("A0902", "Http Method错误")), ABILITYCALL_PARM_ERROR(
            new ExceptionType("A0903", "参数错误")), ABILITYCALL_PARM_SWAGGER_ERROR(new ExceptionType("10064",
            "Swagger内容非json格式")),

    ABILITYCALL_ABILITY_BASE(new ExceptionType("A0001"));

    private ExceptionType exceptionType;

    AbilityExceptionEnum(final ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

    /**
     * 设置exceptionMsg，返回一个新的ExceptionType实例
     * 
     * @param exceptionMsg
     * @return
     */
    public ExceptionType setExceptionMsg(final String exceptionMsg) {

        return new ExceptionType(exceptionType.getExceptionCode(), exceptionMsg);
    }

    /**
     * 设置格式化模板的exceptionMsg<br>
     * eg: 调用{0}系统的{1}接口失败
     * 
     * @param exceptionMsg
     *            模板字符串
     * @param String数组
     *            模板字符串入参
     */
    public ExceptionType setFormatMsg(final String exceptionMsg, final Object... obj) {

        Validate.noNullElements(obj);
        final String msg = MessageFormat.format(exceptionMsg, obj);
        return new ExceptionType(exceptionType.getExceptionCode(), msg);
    }

    public ExceptionType getExceptionType() {

        return exceptionType;
    }

    public void setExceptionType(final ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

}