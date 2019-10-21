package com.daishaowen.test.jwt;

public class Constants {
    /**
     * 基类的AppConfig类需要,为公共服务项目虚拟一个小程序编号为0000
     */
    public static final String COMMON_SERVICE_CODE = "0000";
    /**
     * 登录的令牌键
     */
    public static final String LOGIN_TOKEN_KEY = "ERP-WX-TOKEN";
    /**
     * 登录的小程序编号键
     */
    public static final String MINI_APP_CODE_KEY = "MINI-APP-CODE";
    /**
     * 令牌中的声明[当前用户]键
     */
    public static final String TOKEN_CLAIM_CUR_EMP_KEY = "CUR_EMP";
    /**
     * 令牌中的声明[当前小程序]键
     */
    public static final String TOKEN_CLAIM_CUR_APP_KEY = "CUR_APP";
    /**
     * Redis令牌键格式
     */
    public static final String REDIS_TOKEN_KEY_FORMAT = "[WX-%s]";
    /**
     * Redis员工键格式
     */
    public static final String REDIS_EMP_KEY_FORMAT = "[EMP-%s-%d]";
    /**
     * WebSocket系统管理员名称
     */
    public static final String WS_SYSTEM_ADMIN_NAME = "[CHRIS-LI]";
    /**
     * WebSocket系统管理员主键
     */
    public static final String WS_SYSTEM_ADMIN_ID = "-9999";
    /**
     * WebSocket消息发送到用户的队列名
     */
    public static final String WS_MSG_TO_USER_QUEUE_NAME = "/queue/message";
}
