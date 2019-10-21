package com.daishaowen.test.shejimoshi.yuanxingModel;

/**
 * Created by disvenk.dai on 2018-11-09 14:04
 * 原型模式就是讲一个对象作为原型，使用clone()方法来创建新的实例。
 */
public class YuanXingModel implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone()   {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }

    public static void main ( String[] args){
        YuanXingModel pro = new YuanXingModel();
        YuanXingModel pro1 = (YuanXingModel)pro.clone();
    }
}
