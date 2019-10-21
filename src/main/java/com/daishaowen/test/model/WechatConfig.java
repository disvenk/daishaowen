package com.daishaowen.test.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class WechatConfig implements Serializable {
    private String id;

    private String appid;

    private String appsecret;

    private String cardId;

    private String mchid;

    private String mchkey;

    private String payCertPath;

    private Byte state;

    private static final long serialVersionUID = 1L;
}