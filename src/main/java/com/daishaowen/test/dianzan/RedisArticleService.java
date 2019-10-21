//package com.daishaowen.test.dianzan;
//
//import com.daishaowen.test.canjiacore.entity.JSONResult;
//import com.daishaowen.test.canjiacore.entity.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import java.util.*;
//
//@Component
//public class RedisArticleService {
//
//    @Autowired
//    private JedisPool jedisPool;
//
//    //发布文章
//    public String postArticle(String title,String content,String link,String userId){
//        //文章id,可以是自增，可以是UUID
//        //String articleId = String.valueOf(jedisClientPool.incr("article:"));
//        String articleId = UUID.randomUUID().toString();
//
//        //记录投票用户
//        String voted = "voted:"+articleId;
//        Jedis jedis = jedisPool.getResource();
//        jedis.sadd(voted,userId);//将投票的用户记录到voted:1,一个文章id下有多个用户投票，所以存储多个用户id
//        jedis.expire(voted,DianZanConstants.UNABLE_VOTED_TIME);
//
//        //文章信息记录
//        long now = System.currentTimeMillis()/1000;
//        String article = "article:"+articleId;//生成文章id
//        Map<String,String> articleData = new HashMap<>();
//        articleData.put("title",title);
//        articleData.put("link",link);
//        articleData.put("user",userId);//作者
//        articleData.put("now",String.valueOf(now));//发布时间
//        articleData.put("voted","1");//默认给自己投一票
//        jedis.hmset(article,articleData);
//
//        //zset本身是key 分数 成员，这种格式的，跟hash在field是相反的
//
//        //根据分数排序
//        jedis.zadd("score:info",now+DianZanConstants.VOTE_SCORE,article);
//
//        //根据发布时间排序
//        jedis.zadd("time",now,article);
//        jedis.close();
//        return articleId;
//    }
//
//    //文章投票
//    public Result articleVote(String userId, String article){
//        //计算投票截止时间
//        long cutoff = (System.currentTimeMillis()/1000)-DianZanConstants.UNABLE_VOTED_TIME;
//        //检查是否还可以对文章进行投票，如果该文章的发布时间比截止时间小，则已过期，不能进行投票
//        Jedis jedis = jedisPool.getResource();
//        JSONResult result = new JSONResult();
//        if(jedis.zscore("time",article)<cutoff){//由于此处异常jedis没有被释放导致资源不可用
//            result.setSuccess(false);
//            result.setMessage("投票期限已过，您不能对该文章进行投票");
//            jedis.close();
//            return result;
//        }
//        //获取文章主键id
//        String articleId = article.substring(article.indexOf(":")+1);
//        //将投票的用户加入到键为voted:xx的集合中，表示该用户已投过票了
//        Long sadd = jedis.sadd("voted:" + articleId, userId);
//        if(sadd==1){//记录投票记录
//            //分值加400，zset中对某个成员增加多少值
//            jedis.zincrby("score:info",DianZanConstants.VOTE_SCORE,article);
//
//            //投票数加1，这里是对hash中的某一个值增加多少，这个增加1，这里敢这么直接加1
//            //是因为第一次发布的时候就有了一个值，不然会操作失败
//            jedis.hincrBy(article,"voted",1);
//            jedis.close();
//            result.setSuccess(true);
//            result.setMessage("投票成功");
//            return result;
//        }else if(sadd==0){
//            result.setSuccess(false);
//            result.setMessage("您对该文章已经投过票了哟");
//            jedis.close();
//            return result;
//        }else {
//            result.setSuccess(false);
//            result.setMessage("未知错误");
//            jedis.close();
//            return result;
//        }
//
//    }
//
//    //分页查询文章列表，key传过来是什么就是按什么分页排序
//    public List<Map<String,String>> getArticles(int page,String key){
//        int start =(page-1)*DianZanConstants.ARTICLE_PRE_PAGE;
//        int end =   start+DianZanConstants.ARTICLE_PRE_PAGE-1;
//
//        //倒序查询出投票数最高的文章
//        Jedis jedis = jedisPool.getResource();
//
//        Set<String> ids = jedis.zrevrange(key,start,end);
//        List<Map<String,String>> articles = new ArrayList<>();
//        ids.forEach(n->{
//            Map<String,String> articleData = jedis.hgetAll(n);
//            articleData.put("id",n);
//            articles.add(articleData);
//        });
//        jedis.close();
//        return articles;
//    }
//
//    public Map<String,String> hgetAll(String article){
//        Jedis jedis = jedisPool.getResource();
//        Map<String,String> articleData = jedis.hgetAll(article);
//        jedis.close();
//        return articleData;
//    }
//}
