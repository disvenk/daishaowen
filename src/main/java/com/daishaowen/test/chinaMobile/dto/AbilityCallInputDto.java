package com.daishaowen.test.chinaMobile.dto;

import com.daishaowen.test.chinaMobile.constants.FormatConstants;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

/**
 * AbilityCall request input parm<br>
 * 接受调用方远程调用参数
 * @author wangchao
 *
 */
public class AbilityCallInputDto {

    /**
     * 测试标记<br>
     * 0：非测试交易 1：测试交易
     */
    @Range(min = 0, max = 1, message = "测试标记值非法")
    private Integer testFlag;

    /**
     * 应用ID
     */
    @NotBlank
    @Pattern(regexp = FormatConstants.APP_NO_FORMAT, message = "应用ID格式错误")
    private String appId;

    /**
     * 用户手机号码
     */
    @Pattern(regexp = FormatConstants.PHONE_FORMAT, message = "手机号码格式错误")
    private String userPhoneNumber;

    /**
     * 能力pathUrl |请求URL结尾<br>
     */
    private String urlPath;

    /**
     * 能力调用标示 | 调用方不传<br>
     * 0 生产环境 1 沙盒环境
     */
    private Integer envFlag;

    /**
     * 业务流程编码
     */
    private String bIPCode;

    /**
     * 业务流程版本号
     */
    private String version;

    /**
     * 节点编码
     */
    private String nodeId;

    /**
     * 时间戳（YYYYMMDDHHMMSS）
     */

    @NotBlank(message = "时间戳timestamp不能为空")
    private String timestamp;

    /**
     * 报文流水号(UUID去掉其中的四个减号
     */
    @NotBlank(message = "报文流水号不能为空")
    private String messageId;

    /**
     * 平台令牌token
     */
    private String access_token;

    /**
     * 可选，指定响应格式。可选值：json、xml
     */
    private String format;

    /**
     * 开发者账号
     */
    private String userName;

    /**
     * 用户认证码
     */
    private String userAuthorizationCode;

    /**
     * 消息主体
     */
    private Object content;

    /**
     * 业务流水号
     * @return
     */
    @NotBlank(message = "sessionId不能为空")
    private String sessionId;

    /**
     * header入参
     */
    private RequestHeaderDto header;

    /**
     * 签名
     */
    private String sign;

    /**
     * 是否需要手动设置publicKey 0:需要
     */
    private int isNeedSetPublicKey;

    public RequestHeaderDto getHeader() {

        return header;
    }

    public void setHeader(final RequestHeaderDto header) {

        this.header = header;
    }

    public String getSessionId() {

        return sessionId;
    }

    public void setSessionId(final String sessionId) {

        this.sessionId = sessionId;
    }

    public String getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(final String timestamp) {

        this.timestamp = timestamp;
    }

    public String getMessageId() {

        return messageId;
    }

    public void setMessageId(final String messageId) {

        this.messageId = messageId;
    }

    public String getAccess_token() {

        return access_token;
    }

    public void setAccess_token(final String access_token) {

        this.access_token = access_token;
    }

    public String getFormat() {

        return format;
    }

    public void setFormat(final String format) {

        this.format = format;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(final String userName) {

        this.userName = userName;
    }

    public String getUserAuthorizationCode() {

        return userAuthorizationCode;
    }

    public void setUserAuthorizationCode(final String userAuthorizationCode) {

        this.userAuthorizationCode = userAuthorizationCode;
    }

    public Object getContent() {

        return content;
    }

    public void setContent(final Object content) {

        this.content = content;
    }

    public String getVersion() {

        return version;
    }

    public void setVersion(final String version) {

        this.version = version;
    }

    public Integer getTestFlag() {

        return testFlag;
    }

    public void setTestFlag(final Integer testFlag) {

        this.testFlag = testFlag;
    }

    public String getAppId() {

        return appId;
    }

    public void setAppId(final String appId) {

        this.appId = appId;
    }

    public String getUserPhoneNumber() {

        return userPhoneNumber;
    }

    public void setUserPhoneNumber(final String userPhoneNumber) {

        this.userPhoneNumber = userPhoneNumber;
    }

    public Integer getEnvFlag() {

        return envFlag;
    }

    public void setEnvFlag(final Integer envFlag) {

        this.envFlag = envFlag;
    }

    public String getUrlPath() {

        return urlPath;
    }

    public void setUrlPath(final String urlPath) {

        this.urlPath = urlPath;
    }

    public String getbIPCode() {

        return bIPCode;
    }

    public void setbIPCode(final String bIPCode) {

        this.bIPCode = bIPCode;
    }

    public String getNodeId() {

        return nodeId;
    }

    public void setNodeId(final String nodeId) {

        this.nodeId = nodeId;
    }

    public String getSign() {

        return sign;
    }

    public void setSign(final String sign) {

        this.sign = sign;
    }

    public int getIsNeedSetPublicKey() {

        return isNeedSetPublicKey;
    }

    public void setIsNeedSetPublicKey(final int isNeedSetPublicKey) {

        this.isNeedSetPublicKey = isNeedSetPublicKey;
    }

}
