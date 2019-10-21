package com.daishaowen.test.guanlianchaxunziduan;

import com.daishaowen.test.canjiacore.util.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.management.openmbean.TabularType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class SetFieldValueUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(this.applicationContext==null)
            this.applicationContext=applicationContext;
    }

    public void setValue(Collection col)throws Exception{
        //1.获取结果集的class对象
        Class<?> clazz =col.iterator().next().getClass();
        //2.获取到class对象的属性，为了优化性能，我们定义一个本地缓存
        Field[] fields = clazz.getDeclaredFields();

        Map<String,Object> cache = new HashMap<>();

        //遍历属性集合中被这个注解指定到的字段，再去获取这个注解
        for(Field needField : fields){
            NeedSetValue sv = needField.getAnnotation(NeedSetValue.class);
            //如果没有注解就继续
            if(sv==null)
                continue;
            //需要被设值的字段
            needField.setAccessible(true);

            //通过注解对象去获取到我们需要的四个工具
            /*
            * 那些属性需要设置?
            * 该去调用哪个bean以及哪个方法？
            * 该传入哪个属性的值作为参数？
            * 该获取结果集上哪个属性的值
            * */
            //1.我们去获取需要的bean
            Object bean = this.applicationContext.getBean(sv.beanClass());
            //2.我们要调用的方法
            Method method = sv.beanClass().getMethod(sv.method(),clazz.getDeclaredField(sv.param()).getType());//后面是一个String.class
            //3.获取被当作入参的字段
            Field paramField = clazz.getDeclaredField(sv.param());
            paramField.setAccessible(true);
            //4.查询UserDao后，需要拿到它的name属性字段
            Field targetField = null;
            Boolean needInnerField = !StringUtils.isEmpty(sv.targetFiled());
            //把缓存的key做出来
            String keyPrefix = sv.beanClass()+"_"+sv.method()+"_"+sv.targetFiled()+"_";

            //遍历结果集，开始操作
            for(Object obj:col){
                //获取结果集对象的需要被当做入参的属性
                Object paramValue = paramField.get(obj);
                if(paramValue==null)
                    continue;
                Object value=null;
                String key = keyPrefix+paramValue;
                if(cache.containsKey(key)){
                    value=cache.get(key);
                }else {
                    //调用查询方法得到结果
                    value=method.invoke(bean,paramValue);
                    if(needInnerField){
                        //如果getCustomerName方法返回的是一个字符串，就需要修改一下逻辑，并且，返回值为null时，给空字符串
                        if(value!=null){
                            if(targetField==null){
                                targetField=value.getClass().getDeclaredField(sv.targetFiled());
                                targetField.setAccessible(true);
                            }
                                value=targetField.get(value);
                        }
                    }
                    cache.put(key,value);
                }
                needField.set(obj,value);
            }
        }
    }
}
