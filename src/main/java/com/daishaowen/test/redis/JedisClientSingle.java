//
//
//package com.daishaowen.test.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.Pipeline;
//import redis.clients.jedis.Transaction;
//
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class JedisClientSingle implements JedisClient {
//
//	@Autowired
//	private JedisPool jedisPool;
//
//	//设置
//	@Override
//	public String set(String key, String value) {
//		Jedis jedis = jedisPool.getResource();
//		String result = jedis.set(key, value);
//		jedis.close();
//		return result;
//	}
//
//	//取值
//	@Override
//	public String get(String key) {
//		Jedis jedis = jedisPool.getResource();
//		String result = jedis.get(key);
//		jedis.close();
//		return result;
//	}
//
//	//判断是否存在
//	@Override
//	public Boolean exists(String key) {
//		Jedis jedis = jedisPool.getResource();
//		Boolean result = jedis.exists(key);
//		jedis.close();
//		return result;
//	}
//
//	//设置过期时间
//	@Override
//	public Long expire(String key, int seconds) {
//		Jedis jedis = jedisPool.getResource();
//		Long result = jedis.expire(key, seconds);
//		jedis.close();
//		return result;
//	}
//
//	@Override
//	public Long ttl(String key) {
//		Jedis jedis = jedisPool.getResource();
//		Long result = jedis.ttl(key);
//		jedis.close();
//		return result;
//	}
//
//	@Override
//	public Long incr(String key) {
//		Jedis jedis = jedisPool.getResource();
//		Long result = jedis.incr(key);
//		jedis.close();
//		return result;
//	}
//
//	@Override
//	public Long hset(String key, String field, String value) {
//		Jedis jedis = jedisPool.getResource();
//		Long result = jedis.hset(key, field, value);
//		jedis.close();
//		return result;
//	}
//
//	@Override
//	public String hget(String key, String field) {
//		Jedis jedis = jedisPool.getResource();
//		String result = jedis.hget(key, field);
//		jedis.close();
//		if(result==null || "".equals(result)){
//			return "0";
//		}
//		return result;
//	}
//
//	@Override
//	public Long hdel(String key, String... field) {
//		Jedis jedis = jedisPool.getResource();
//		Long result = jedis.hdel(key, field);
//		jedis.close();
//		return result;
//	}
//
//	//监听
//	@Override
//	public String watch(String key) {
//		Jedis jedis = jedisPool.getResource();
//		String watch = jedis.watch(key);
//		jedis.close();
//		return watch;
//	}
//
//	//事务
//	@Override
//	public Transaction multi() {
//		Jedis jedis = jedisPool.getResource();
//		Transaction multi = jedis.multi();
//		jedis.close();
//		return multi;
//	}
//
//	@Override
//	public String hmset(String key, Map<String,String> hash){
//		Jedis jedis = jedisPool.getResource();
//		String hmset = jedis.hmset(key, hash);
//		jedis.close();
//		return hmset;
//	}
//
//	//防攻击专用,时间是秒
//	@Override
//	public Long add(String key, String field,Integer count,int timeout){
//		String coutStr = count==null? "0":count.toString();
//		Jedis jedis = jedisPool.getResource();
//		Long result = jedis.hset(key, field,coutStr);
//		jedis.expire(key,timeout);
//		jedis.close();
//		return result;
//	}
//
//	public void dels(){
//	    Jedis jedis = jedisPool.getResource();
//        Pipeline pipelined = jedis.pipelined();
//        for(int i=0;i<10;i++){
//            pipelined.del("");
//        }
//        pipelined.sync();
//        jedis.close();
//    }
//}
//
//
//
