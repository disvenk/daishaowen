package com.daishaowen.test.redis;

import redis.clients.jedis.Transaction;

import java.util.Map;

public interface JedisClient {

	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
	String watch(String key);
	Transaction multi();
	String hmset(String key, Map<String,String> hash);
	Long add(String key, String field,Integer count,int timeout);
}
