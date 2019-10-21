package com.daishaowen.test.shejimoshi.strategyModel.tushulizi;

/**
 * Created by disvenk.dai on 2018-11-05 16:52
 * 外部客户端
 */
public class Client {

    public static void main(String[] args) {
        //选择并创建需要使用的策略对象
        MemberStrategy strategy = new AdvancedMemberStrategy();
        //创建环境
        Price price = new Price(strategy);
        //计算价格
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
    }

}
