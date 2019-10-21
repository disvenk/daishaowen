package com.daishaowen.test.shejimoshi.strategyModel.tushulizi;

/**
 * Created by disvenk.dai on 2018-11-05 16:47
 * 抽象折扣类(抽象策略类)
 */
public interface MemberStrategy {
    /**
     * 计算图书的价格
     * @param booksPrice    图书的原价
     * @return    计算出打折后的价格
     */
    public double calcPrice(double booksPrice);
}
