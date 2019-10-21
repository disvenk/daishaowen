package com.daishaowen.test.shejimoshi.xiangyuanModel.simpleXiangyuan;

import com.daishaowen.test.shejimoshi.xiangyuanModel.Flyweight;

/**
 * Created by disvenk.dai on 2018-11-16 15:45
 */
//具体享元角色类
//具体享元角色类ConcreteFlyweight有一个内部状态，在本例中一个Character类型的intrinsicState属性代表，它的值应当在享元对象
//被创建时赋予。所有的内部状态在对象创建之后，就不会再改变了。如果一个享元对象有外部状态的话，所有的外部状态都必须存储在客户端，
//在使用享元对象时，再由客户端传入享元对象。这里只有一个外部状态，operation()方法的参数state就是由外部传入的外部状态。
public class ConcreteFlyweight implements Flyweight {
    private Character intrinsicState = null;


    /**
     * 构造函数，内部状态作为参数传入，在这个享元对象初始化时传入这个内部状态参数后，以后再也不能改了
     * @param state
     */
    public ConcreteFlyweight(Character state){
        this.intrinsicState = state;
    }

    /**
     * 外部状态作为参数传入方法中，改变方法的行为，这个外部状态可以无限的改变，只要调用这个方法，传入不同的参数
     * 外部状态就有不同的状态
     * 但是并不改变对象的内部状态。
     */
    @Override
    public void operation(String state) {
        // TODO Auto-generated method stub
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }

}
