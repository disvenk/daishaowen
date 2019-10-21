package com.daishaowen.test.yunshengerp.javacommon.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;


public class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7200095849148417467L;

	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected static final String TIME_FORMAT = "HH:mm:ss";
	
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	
    // 定义扩展属性的Map
    private Map<String, Object> attrs = new HashMap<String, Object>();

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }
    
    // 放入扩展属性
    public void putAttr(String key, Object value) {
        attrs.put(key, value);
    }
    
    // 获取扩展属性
    public Object getAttr(String key) {
        return attrs.get(key);
    }
    
    public String toJSON(){
        SerializerFeature[] serializerFeature = new SerializerFeature[] {
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.BrowserCompatible,
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullBooleanAsFalse
                };
        
        String jsonString = JSON.toJSONString(this, serializerFeature);
        return jsonString;
    }
    
}
