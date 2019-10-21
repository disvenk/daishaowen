package com.daishaowen.test.shejimoshi.xiangyuanModel.ZuheXiangyuan;

import com.daishaowen.test.shejimoshi.xiangyuanModel.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by disvenk.dai on 2018-11-16 16:02
 * 复合享元角色
 */
public class ConcreteCompositeFlyweight implements Flyweight {
    //这里相当于对象里又包了一层缓存
    private Map<Character,Flyweight> files = new HashMap<Character,Flyweight>();
    /**
     * 增加一个新的单纯享元对象到聚集中
     */
    public void add(Character key , Flyweight fly){
        //这里存的是ConcreteFlyweight对象
        files.put(key,fly);
    }
    /**
     * 外蕴状态作为参数传入到方法中
     */
    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for(Object o : files.keySet()){
            fly = files.get(o);
            fly.operation(state);
        }

    }

}
