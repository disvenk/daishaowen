//package com.daishaowen.test.controller;
//
//import com.daishaowen.test.redis.JedisClientCluster;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by disvenk.dai on 2018-08-13 10:23
// */
//
//@RestController
//public class RedisController {
//
////    @Autowired
////    JedisClientPool jedisClientPool;
////
////    @RequestMapping("redis")
////    public String set(){
////       jedisClientPool.watch("hello");
////        Transaction multi = jedisClientPool.multi();
////        jedisClientPool.incr("hello");
////        try{
////            List<Object> exec = multi.exec();
////        }catch (Exception e){
////            System.out.println(Thread.currentThread().getName()+"操作失败");
////        }
////
////        return null;
////    }
//
//    @Autowired
//    JedisClientCluster jedisClientCluster;
//
//    @RequestMapping("redis")
//    public String set(){
//        String name = jedisClientCluster.get("name");
//        System.out.println(name);
//        String age = jedisClientCluster.set("age", "55");
//        return age;
//    }
//
//
//}
