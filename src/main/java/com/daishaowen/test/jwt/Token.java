package com.daishaowen.test.jwt;

import com.daishaowen.test.treesetquchongpaixu.Student;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Token {

    //登录失效时间(单位为秒)
    @Value("${wechat.login.timeout:7200}")
    private String loginTimeout;

    @Value("${wechat.jwt.time.format:yyyyMMddHHmmss}")
    private String tokenTimeFormat;

    /**
     * 缓存登录时间(单位为秒)
     */
    @Value("${wechat.cache.timeout:604800}")
    private String cacheTimeout;

    private String buildToken(Student loginEmployeeVo) {
        Map<String, String> claimMap = new HashMap<String, String>(2);
        claimMap.put(Constants.TOKEN_CLAIM_CUR_EMP_KEY, JsonUtil.beanToJson(loginEmployeeVo));
        //claimMap.put(Constants.TOKEN_CLAIM_CUR_APP_KEY, loginEmployeeVo.getLoginMiniAppCode());
        String token = JwtUtil.createToken(claimMap, tokenTimeFormat, Long.valueOf(loginTimeout) * 1000);
        String md5Token = EncryptUtil.getMd5(token);
        String redisTokenKey = String.format(Constants.REDIS_TOKEN_KEY_FORMAT, md5Token);

        // 存入Redis数据库,并设置失效时间
        //redisTemplate.opsForHash().put(redisTokenKey, "jwt", jwt);
       // redisTemplate.opsForHash().put(redisTokenKey, "curEmp", JsonUtil.beanToJson(loginEmployeeVo));
       // redisTemplate.expire(redisTokenKey, loginEmployeeVo.getCacheTimeout(), TimeUnit.SECONDS);
        //redisTemplate.opsForValue().set(String.format(Constants.REDIS_EMP_KEY_FORMAT, loginEmployeeVo.getLoginMiniAppCode(), loginEmployeeVo.getId()), jwt, loginEmployeeVo.getCacheTimeout(), TimeUnit.SECONDS);
        return token;
    }

    private void auth(HttpServletRequest request){

    }
}
