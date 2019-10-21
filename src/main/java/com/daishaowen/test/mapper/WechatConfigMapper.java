package com.daishaowen.test.mapper;

import com.daishaowen.test.model.WechatConfig;

public interface WechatConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(WechatConfig record);

    int insertSelective(WechatConfig record);

    WechatConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WechatConfig record);

    int updateByPrimaryKey(WechatConfig record);
}