package com.daishaowen.test.guanlianchaxunziduan;

public class Order {
    private String id;

    private String customerId;

    @NeedSetValue(beanClass=UserDao.class,param = "customerId",method = "getCustomerName",targetFiled = "name")
    private String customerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
