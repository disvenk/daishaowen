package com.daishaowen.test.shejimoshi.xiangyuanModel.ZuheXiangyuan;

import com.daishaowen.test.shejimoshi.xiangyuanModel.Flyweight;

/**
 * Created by disvenk.dai on 2018-11-16 16:00
 * 单纯享元角色
 */
public class ConcreteFlyweight implements Flyweight {
    private Character intrinsicState = null;
    /**
     * 构造函数，内部状态作为参数传入
     * @param state
     */
    public ConcreteFlyweight(Character state){
        this.intrinsicState = state;
    }

    /**
     * 外b部状态作为参数传入方法中，改变方法的行为，
     * 但是并不改变对象的内部状态。
     */
    @Override
    public void operation(String state) {
        // TODO Auto-generated method stub
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }

}
