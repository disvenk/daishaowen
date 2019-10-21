package com.daishaowen.test.devTest;

import java.util.Date;

public class Ngnix {
    public Integer orders;
    public String clientIP;
    public Date localTime;
    public String requestType;
    public String requestUrl;
    public String stutasCode;
    public String responseByte;
    public String requestPage;
    public String clientEquipment;
    public Double costTime;
    public String requestBody;
    public Long userId;

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public Date getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Date localTime) {
        this.localTime = localTime;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getStutasCode() {
        return stutasCode;
    }

    public void setStutasCode(String stutasCode) {
        this.stutasCode = stutasCode;
    }

    public String getResponseByte() {
        return responseByte;
    }

    public void setResponseByte(String responseByte) {
        this.responseByte = responseByte;
    }

    public String getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(String requestPage) {
        this.requestPage = requestPage;
    }

    public String getClientEquipment() {
        return clientEquipment;
    }

    public void setClientEquipment(String clientEquipment) {
        this.clientEquipment = clientEquipment;
    }

    public Double getCostTime() {
        return costTime;
    }

    public void setCostTime(Double costTime) {
        this.costTime = costTime;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Ngnix{" +
                "orders=" + orders +
                ", clientIP='" + clientIP + '\'' +
                ", localTime='" + localTime + '\'' +
                ", requestType='" + requestType + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", stutasCode='" + stutasCode + '\'' +
                ", responseByte=" + responseByte +
                ", requestPage='" + requestPage + '\'' +
                ", clientEquipment='" + clientEquipment + '\'' +
                ", costTime='" + costTime + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", userId=" + userId +
                '}';
    }
}
