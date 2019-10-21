package com.daishaowen.test.devTest;

public class Tomcat {
    public Integer orders;
    public String startTime;
    public Integer finalTime;
    public String sqlStr;
    public String orginContent;

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Integer finalTime) {
        this.finalTime = finalTime;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getOrginContent() {
        return orginContent;
    }

    public void setOrginContent(String orginContent) {
        this.orginContent = orginContent;
    }

    @Override
    public String toString() {
        return "Tomcat{" +
                "orders=" + orders +
                ", startTime='" + startTime + '\'' +
                ", finalTime='" + finalTime + '\'' +
                ", sqlStr='" + sqlStr + '\'' +
                ", orginContent='" + orginContent + '\'' +
                '}';
    }
}
