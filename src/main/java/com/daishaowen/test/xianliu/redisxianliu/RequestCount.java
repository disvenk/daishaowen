//package com.daishaowen.test.xianliu;
//
//import com.daishaowen.test.redis.JedisClientSingle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class RequestCount {
//
//    @Autowired
//    JedisClientSingle jedisClientPool;
//
//    public void ipCount(String ip,Integer count){
//        String curDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String key = curDate+":"+ip;
//        Integer oldCount = Integer.parseInt(jedisClientPool.hget(key,ip));
//        oldCount = oldCount+count;
//        //这里之所以不用hincrBy是因为有可能第一次进来没有值
//        jedisClientPool.add(key,ip,oldCount,ConstantXianLiu.timeout);
//    }
//
//    public boolean isLimitIp(String ip){
//        String key = new SimpleDateFormat("yyyyMMdd").format(new Date())+":"+ip;
//        Integer count = Integer.parseInt(jedisClientPool.hget(key,ip));
//        if(count>=ConstantXianLiu.ipLimitCount){
//            return true;
//        }
//        return false;
//    }
//}
