package com.daishaowen.test.shejimoshi.strategyModel.tushulizi;

/**
 * Created by disvenk.dai on 2018-11-05 16:49
 * 高级会员折扣类(具体策略类)
 */
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}
