package com.daishaowen.test.shejimoshi.xiangyuanModel.ZuheXiangyuan;

import com.daishaowen.test.shejimoshi.xiangyuanModel.Flyweight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by disvenk.dai on 2018-11-16 16:05
 */
public class FlyweightFactory {
    private Map<Character,Flyweight> files = new HashMap<Character,Flyweight>();
    /**
     * 复合享元工厂方法
     */
    public Flyweight factory(List<Character> compositeState){
        //复合享元对象不能共享，看这里就知道经过了多次的实例化
        ConcreteCompositeFlyweight compositeFly = new ConcreteCompositeFlyweight();

        for(Character state : compositeState){
            //这里传入的是ConcreteFlyweight对象
            compositeFly.add(state,this.factory(state));//最终还是要调用单纯享元工厂
        }

        return compositeFly;
    }
    /**
     * 单纯享元工厂方法
     */
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
