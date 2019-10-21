package com.daishaowen.test.shejimoshi.xiangyuanModel.simpleXiangyuan;

import com.daishaowen.test.shejimoshi.xiangyuanModel.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by disvenk.dai on 2018-11-16 15:48
 */
//享元工厂角色类
//享元工厂角色类，必须指出的是，客户端不可以直接将具体享元类实例化，而必须通过一个工厂对象，利用一个factory()方法得到享元对象。
//一般而言，享元工厂对象在整个系统中只有一个，因此也可以使用单例模式。
//当客户端需要单纯享元对象的时候，需要调用享元工厂的factory()方法，并传入所需的单纯享元对象的内部状态，由工厂方法产生所需要的
//享元对象。
//此例中享元工厂具有局限性，只能创建某一种对象，可以使用泛型和反射的方式实现通用型
public class FlyweightFactory {
    //以内部状态为key，实例对象为value存储
    private Map<Character,Flyweight> files = new HashMap<Character,Flyweight>();

    public Flyweight factory(Character state){
        //先从缓存中查找对象
        Flyweight fly = files.get(state);
        if(fly == null){
            //如果对象不存在则创建一个新的Flyweight对象
            fly = new ConcreteFlyweight(state);
            //把这个新的Flyweight对象添加到缓存中
            files.put(state, fly);
        }
        return fly;
    }
}
