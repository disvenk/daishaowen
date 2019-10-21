package com.daishaowen.test.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class ElectronicTicketConfig implements Serializable {
    private Long id;

    private String brandId;

    private String appkey;

    private String appsecret;

    private String address;

    private String name;

    private String operator;

    private String wechatPayNum;

    private String bankNum;

    private String bank;

    private String payeeRegisterNo;

    private String payeeChecker;

    private String payeeReceiver;

    private String payeePhone;

    private String email;

    private String authorizationKey;

    private static final long serialVersionUID = 1L;
}