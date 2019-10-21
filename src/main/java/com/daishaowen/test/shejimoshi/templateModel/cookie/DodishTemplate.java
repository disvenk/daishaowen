package com.daishaowen.test.shejimoshi.templateModel.cookie;

/**
 * Created by disvenk.dai on 2018-11-06 17:29
 * 来举个例子： 比如我们做菜可以分为三个步骤 （1）备料
 * （2）具体做菜
 * （3）盛菜端给客人享用，这三部就是算法的骨架 ；然而做不同菜需要的料，做的方法，以及如何盛装给客人享用都是不同的这个就是不同的实现细节。
 * 抽象的做菜父类
 */
public abstract class DodishTemplate {
    /**
     * 具体的整个过程
     */
    protected void dodish(){
        this.preparation();
        this.doing();
        this.carriedDishes();
    }
    /**
     * 备料
     */
    public abstract void preparation();
    /**
     * 做菜
     */
    public abstract void doing();
    /**
     * 上菜
     */
    public abstract void carriedDishes ();
}
