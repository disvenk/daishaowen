//package com.daishaowen.test.jwt;
//
//import com.auth0.jwt.exceptions.InvalidClaimException;
//import com.phone.erp.base.annotation.AuthValidate;
//import com.phone.erp.base.config.AppConfig;
//import com.phone.erp.base.config.DevModelConfig;
//import com.phone.erp.base.enums.ErrorCode;
//import com.phone.erp.base.enums.MiniApp;
//import com.phone.erp.base.utils.Assert;
//import com.phone.erp.base.vo.employee.LoginEmployeeVo;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * [权限切面]
// *
// * @author Chris li[黎超]
// * @version [版本, 2017-04-12]
// * @see
// */
//@Aspect
//@Component
//public class AuthAspect extends BaseAspect {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private DevModelConfig devModelConfig;
//
//    @Autowired
//    private AppConfig appConfig;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Pointcut("@annotation(com.phone.erp.base.annotation.AuthValidate) && @annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * com.phone.erp..controller..*Controller.*(..))")
//    public void authAspect() {
//
//    }
//
//    @Before("authAspect()")
//    public void beforeMethod(JoinPoint joinPoint) {
//        if (devModelConfig.isDevModel()) {
//            // 开发模式无需验证用户登录,小程序编号合法性及用户权限等
//            return;
//        }
//        // 从请求中获取当前登录人信息
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        // 从header中获取token
//        String jwt = request.getHeader(Constants.LOGIN_TOKEN_KEY);
//        // 如果header中不存在token，则从参数中获取token
//        if (StringUtils.isBlank(jwt)) {
//            jwt = request.getParameter(Constants.LOGIN_TOKEN_KEY);
//        }
//        // token为空,未登录,需微信小程序端自动登录
//        Assert.notBlank(jwt, ErrorCode.NOT_LOGGED_IN);
//        String currentEmployeeVoJson = "";
//        LoginEmployeeVo curEmp = null;
//        try {
//            // 验证令牌是否过期
//            currentEmployeeVoJson = JwtUtil.verifyToken(jwt, Constants.TOKEN_CLAIM_CUR_EMP_KEY);
//            String currentMiniAppCode = JwtUtil.verifyToken(jwt, Constants.TOKEN_CLAIM_CUR_APP_KEY);
//            // 验证小程序编号合法性
//            MiniApp.getMatchedInstance(currentMiniAppCode);
//            // 如果当前调用本代码的项目是公共服务项目,则任何小程序都能通过验证
//            if (!appConfig.getMiniAppCode().equals(Constants.COMMON_SERVICE_CODE)) {
//                Assert.isTrue(appConfig.getMiniAppCode().equals(currentMiniAppCode), ErrorCode.MINI_APP_CODE_ILLEGAL);
//            }
//            curEmp = JsonUtil.jsonToBean(currentEmployeeVoJson, LoginEmployeeVo.class);
//        } catch (InvalidClaimException e) {
//            // 令牌过期
//            String cacheKey = String.format(Constants.REDIS_TOKEN_KEY_FORMAT, EncryptUtil.getMd5(jwt));
//            logger.info(String.format("令牌键%s已失效,检查是否超出缓存登录时间!", cacheKey));
//            boolean hasKey = redisTemplate.hasKey(cacheKey);
//            logger.info(String.format("查询Redis键%s --- %s!", cacheKey, hasKey ? "存在" : "不存在"));
//            Assert.isTrue(hasKey, ErrorCode.NOT_LOGGED_IN);
//            // 缓存登录未过期
//            Object tokenValue = redisTemplate.opsForHash().get(cacheKey, "curEmp");
//            currentEmployeeVoJson = tokenValue.toString();
//            curEmp = JsonUtil.jsonToBean(currentEmployeeVoJson, LoginEmployeeVo.class);
//            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//            response.setHeader("refresh", "yes");
//            response.setHeader("jwt", buildToken(curEmp));
//            // 设置旧令牌10秒后清除
//            redisTemplate.expire(cacheKey, 10, TimeUnit.SECONDS);
//            logger.info(String.format("Redis清除失效令牌对应的缓存登录Key%s!", cacheKey));
//        }
//        // 将当前用户JSON串放入请求中
//        request.setAttribute(Constants.TOKEN_CLAIM_CUR_EMP_KEY, currentEmployeeVoJson);
//
//        Method sourceMethod = getSourceMethod(joinPoint);
//        AuthValidate validate = sourceMethod.getAnnotation(AuthValidate.class);
//        // TODO 验证权限
//        logger.debug(curEmp.toString());
//        logger.debug(validate.toString());
//    }
//
//    /**
//     * [生成授权令牌]
//     *
//     * @author Chris li[黎超]
//     * @create [2017-04-12]
//     */
//    private String buildToken(LoginEmployeeVo loginEmployeeVo) {
//        Map<String, String> claimMap = new HashMap<String, String>(2);
//        claimMap.put(Constants.TOKEN_CLAIM_CUR_EMP_KEY, JsonUtil.beanToJson(loginEmployeeVo));
//        claimMap.put(Constants.TOKEN_CLAIM_CUR_APP_KEY, loginEmployeeVo.getLoginMiniAppCode());
//        String jwt = JwtUtil.createToken(claimMap, loginEmployeeVo.getTokenTimeFormat(), loginEmployeeVo.getLoginTimeout() * 1000);
//        String md5Token = EncryptUtil.getMd5(jwt);
//        String redisTokenKey = String.format(Constants.REDIS_TOKEN_KEY_FORMAT, md5Token);
//        logger.info(String.format("当前登录员工[%d],缓存登录Key%s", loginEmployeeVo.getId(), redisTokenKey));
//        // 存入Redis数据库,并设置失效时间
//        redisTemplate.opsForHash().put(redisTokenKey, "jwt", jwt);
//        redisTemplate.opsForHash().put(redisTokenKey, "curEmp", JsonUtil.beanToJson(loginEmployeeVo));
//        redisTemplate.expire(redisTokenKey, loginEmployeeVo.getCacheTimeout(), TimeUnit.SECONDS);
//        redisTemplate.opsForValue().set(String.format(Constants.REDIS_EMP_KEY_FORMAT, loginEmployeeVo.getLoginMiniAppCode(), loginEmployeeVo.getId()), jwt, loginEmployeeVo.getCacheTimeout(), TimeUnit.SECONDS);
//        return jwt;
//    }
//}