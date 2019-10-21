package com.daishaowen.test.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

public class InventoryBeanUtils {
    /**
     * 将查询结果集<Map>转换成指定对象
     *
     * @param <T>
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            setFieldsValue(clazz, t, map);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 递归为对象设值
     *
     * @param <T>
     * @param clazz
     * @param t
     * @param map
     * @throws Exception
     */
    private static <T> void setFieldsValue(Class<T> clazz, T t, Map<String, Object> map) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = map.get(field.getName().toUpperCase());
            // 类型不匹配时,需要转换
            changeValueTypeAndSetValue(t, field, value);
        }
        if (clazz.getSuperclass() == Object.class) {
            return;
        }
        setFieldsValue(clazz.getSuperclass(), t, map);
    }

    /**
     * 转换值类型并设值
     *
     * @param field
     * @param value
     * @throws Exception
     */
    private static <T> void changeValueTypeAndSetValue(T t, Field field, Object value) throws Exception {
        if (value == null || field.getType() == value.getClass()) {
            field.set(t, value);
            return;
        }
        if (field.getType() == String.class) {
            field.set(t, String.valueOf(value));
            return;
        }
        if (field.getType() == Long.class) {
            field.set(t, Long.valueOf(value.toString()).longValue());
            return;
        }
        if (field.getType() == Integer.class) {
            field.set(t, (Integer.valueOf(value.toString())).intValue());
            return;
        }
        if (field.getType() == Double.class) {
            field.set(t, (Double.valueOf(value.toString())).doubleValue());
            return;
        }
        field.set(t, (Date) value);
    }
}
