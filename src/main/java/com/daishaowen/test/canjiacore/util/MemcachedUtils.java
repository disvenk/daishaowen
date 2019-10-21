//package com.daishaowen.test.canjiacore.util;
//
//import net.spy.memcached.MemcachedClient;
//import net.spy.memcached.OperationTimeoutException;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.util.concurrent.ExecutionException;
//
///**
// * Created by KONATA on 2017/2/15.
// */
//public class MemcachedUtils {
//    private static final int expiredSeconds = 600;
//
//    private static String url = "m-uf6d708453db2264.memcache.rds.aliyuncs.com";
////    private  static  String url = "139.196.48.215";
//
//
//    public static int port = 11211;
//
//    private final static InetSocketAddress server = new InetSocketAddress(url,
//            port);
//
//    private static MemcachedClient memcachedClient;
//
//    public static void init() {
//        try {
//            memcachedClient = new MemcachedClient(server);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void put(String key, Object obj) {
//        if (memcachedClient == null) {
//            init();
//        }
//        memcachedClient.set(key, expiredSeconds, obj);
//    }
//
//    public static void put(String key, Object obj,int time) {
//        if (memcachedClient == null) {
//            init();
//        }
//        memcachedClient.set(key, time, obj);
//    }
//
//    public static boolean add(String key, Object obj){
//        if(memcachedClient == null){
//            init();
//        }
//        try {
//            return memcachedClient.add(key,expiredSeconds,obj).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static boolean add(String key, Object obj,int time){
//        if(memcachedClient == null){
//            init();
//        }
//        try {
//            return memcachedClient.add(key,time,obj).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static void delete(String key) {
//        if (memcachedClient == null) {
//            init();
//        }
//        memcachedClient.delete(key);
//    }
//
//    public static Object get(String key) {
//        if (memcachedClient == null) {
//            init();
//        }
//        Object o;
//        try {
//            o = memcachedClient.get(key);
//        } catch (OperationTimeoutException e) {
//            o = memcachedClient.get(key);
//        }
//        return o;
//    }
//
//    public  static  void flus(){
//        if(null==memcachedClient){
//            init();
//        }
//        memcachedClient.flush();
//    }
//
//    public  static  void clean(String brandId, String brandSign){
//        delete(brandSign);
//        delete(brandId + "shopList");
//        delete(brandId + "setting");
//    }
//
//    public static void main(String[] args) {
//        System.out.println(add("f48a0a35e0be4dd8aaeb7cf7276039581118f0908a094bbc9988203e60a73d68","1",30));
//        System.out.println(get("f48a0a35e0be4dd8aaeb7cf7276039581118f0908a094bbc9988203e60a73d68"));
//    }
//}
