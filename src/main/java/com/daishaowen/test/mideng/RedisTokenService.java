//package com.daishaowen.test.mideng;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
///**
// * 生成token并且放到redis中
// */
//@Component
//public class RedisTokenService {
//    private static final Integer TOKEN_TIMEOUT = 600;
//
//    @Autowired
//    RedisService redisService;
//
//    public String getToken() {
//        String jwt = "jwt" + UUID.randomUUID();
//        redisService.set(jwt, jwt, TOKEN_TIMEOUT);
//        return jwt;
//    }
//
//    public boolean findToken(String tokenKey){
//        String jwt = redisService.get(tokenKey);
//        if(StringUtils.isEmpty(jwt)){
//            return false;
//        }
//        redisService.del(tokenKey);
//        return true;
//    }
//
//}
