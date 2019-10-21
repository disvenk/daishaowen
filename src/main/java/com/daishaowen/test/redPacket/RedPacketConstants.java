package com.daishaowen.test.redPacket;

public class RedPacketConstants {

    public static final int THREAD_NUMBER = 20;//创建红包的线程数

    //  -- 函数：尝试获得红包，如果成功，则返回json字符串，如果不成功，则返回空
//  -- 参数：红包队池名， 已消费的队列名(记录大红包下每个用户抢了多少钱)，判断是否抢过的队列名hash，用户ID
//  -- 返回值：nil 或者 json字符串，包含用户ID：userId，红包ID：id，红包金额：money
    public static final String tryGetHongBaoScript = //"local bConsumed = redis.call('hexists', KEYS[3], KEYS[4]);\n"
//			+ "print('bConsumed:' ,bConsumed);\n"
            //查询用户是否已经抢过红包，如果用户已经抢过红包，则直接返回nil
			"if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"
                    + "return nil\n"
                    + "else\n"
                    //从红包池中取出一个小红包
                    + "local hongBao = redis.call('rpop', KEYS[1]);\n"
//			+ "print('hongBao:', hongBao);\n"
                    //判断红包是否不为空
                    + "if hongBao then\n"
                    + "local x = cjson.decode(hongBao);\n"
                    //将红包信息与用户ID信息绑定，表示用户已抢到该红包
                    //将红包信息转正json后再把userId追加进去
                    + "x['userId'] = KEYS[4];\n"
                    + "local re = cjson.encode(x);\n"
                    //记录用户已抢过的hash
                    + "redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"
                    //将每个用户抢到的金额记录在大红包的集合下，存储的是红包详情
                    + "redis.call('lpush', KEYS[2], re);\n"
                    + "return re;\n"
                    + "end\n"
                    + "end\n"
                    + "return nil";
}
