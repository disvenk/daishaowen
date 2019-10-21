//package com.daishaowen.test.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.Transaction;
//
//import java.util.Map;
//
//@Component
//public class JedisClientCluster implements JedisClient {
//
//	@Autowired
//	private JedisCluster jedisCluster;
//
//	@Override
//	public String set(String key, String value) {
//		return jedisCluster.set(key, value);
//	}
//
//	@Override
//	public String get(String key) {
//		return jedisCluster.get(key);
//	}
//
//	@Override
//	public Boolean exists(String key) {
//		return jedisCluster.exists(key);
//	}
//
//	@Override
//	public Long expire(String key, int seconds) {
//		return jedisCluster.expire(key, seconds);
//	}
//
//	@Override
//	public Long ttl(String key) {
//		return jedisCluster.ttl(key);
//	}
//
//	@Override
//	public Long incr(String key) {
//		return jedisCluster.incr(key);
//	}
//
//	@Override
//	public Long hset(String key, String field, String value) {
//		return jedisCluster.hset(key, field, value);
//	}
//
//	@Override
//	public String hget(String key, String field) {
//		return jedisCluster.hget(key, field);
//	}
//
//	@Override
//	public Long hdel(String key, String... field) {
//		return jedisCluster.hdel(key, field);
//	}
//
//    @Override
//    public String watch(String key) {
//        return null;
//    }
//
//    @Override
//    public Transaction multi() {
//        return null;
//    }
//
//    @Override
//    public String hmset(String key, Map<String, String> hash) {
//        return null;
//    }
//
//    @Override
//    public Long add(String key, String field, Integer count, int timeout) {
//        return null;
//    }
//
//}
