//package com.daishaowen.test.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.daishaowen.test.canjiacore.entity.JSONResult;
//import com.daishaowen.test.canjiacore.entity.Result;
//import com.daishaowen.test.redPacket.RedPackagePool;
//import com.daishaowen.test.redPacket.RedPacketConstants;
//import org.apache.commons.lang3.time.StopWatch;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.CountDownLatch;
//
//@RestController
//public class RedPacketController {
//
//    @Autowired
//    JedisPool jedisPool;
//
//    @Autowired
//    RedPackagePool redPackagePool;
//
//    @RequestMapping("createBigAndSmallPacket")
//    public Result createBigAndSmallPacket(BigDecimal totalMoney,Integer num) throws InterruptedException {
//        String redPacketId = UUID.randomUUID().toString();//生成大红包id
//        System.out.println(redPacketId);
//        String redPacketPool = "redPacketPool:"+redPacketId;
//        BigDecimal[] redPackages = redPackagePool.getStarted(totalMoney, num);
//        Jedis jedis = jedisPool.getResource();
//        for(int i = 0; i < redPackages.length; ++i) {
//                    JSONObject object = new JSONObject();
//                        object.put("id", UUID.randomUUID().toString());//生成小红包id
//                        object.put("money", redPackages[i]);
//                        jedis.lpush(redPacketPool, object.toJSONString());//关联大红包id
//            }
//            jedis.close();
//        JSONResult jsonResult = new JSONResult();
//        jsonResult.setSuccess(true);
//        jsonResult.setMessage("发出红包成功");
//        return jsonResult;
//    }
//
//    @RequestMapping("getRedPacket")
//    public Result getRedPacket(String userId, String redPacketId){
//        StopWatch watch = new StopWatch();//计时器
//        Jedis jedis = jedisPool.getResource();
//        //红包队列名
//        String redPacketPool = "redPacketPool:"+redPacketId;
//        //大红包下用户抢到红包的队列
//        String userGetHongBaoInfo = "userGetHongBaoInfo:"+redPacketId;
//        //判断用户是否抢过的hash
//        String isGetHongBao = "isGetHongBao:"+redPacketId;
//
//        //抢之前先后判断红包是否还有和是否已经抢过，如果没有了就提示已经抢完，如果抢过了就直接显示列表
//        //列表头上显示自己抢到的金额
//        JSONResult jsonResult = new JSONResult();
//        String isGet = jedis.hget(isGetHongBao, userId);
//        if(isGet!=null || "".equals(isGet)){
//            List<String> redPackets = jedis.lrange(userGetHongBaoInfo, 0, -1);
//            jsonResult.setSuccess(false);
//            jsonResult.setMessage("您已经抢过了！");
//            jsonResult.setData(redPackets);
//            jedis.close();
//            return jsonResult;
//        }
//
//        //eval里面是用户抢到的金额对象
//        try{
//            Object eval = jedis.eval(RedPacketConstants.tryGetHongBaoScript, 4, redPacketPool, userGetHongBaoInfo, isGetHongBao, userId);
//            if(eval==null || "".equals(eval)){
//                jsonResult.setSuccess(false);
//                jsonResult.setMessage("手慢啦，红包已经被抢完啦！");
//                return jsonResult;
//            }
//            jsonResult.setSuccess(true);
//            jsonResult.setMessage("成功抢到红包");
//            jsonResult.setData(eval);
//            return jsonResult;
//        }catch (Exception e){
//            e.printStackTrace();
//            jsonResult.setSuccess(false);
//            jsonResult.setMessage("手慢啦，红包已经被抢完啦！");
//            return jsonResult;
//        }finally {
//            jedis.close();
//        }
//    }
//}
