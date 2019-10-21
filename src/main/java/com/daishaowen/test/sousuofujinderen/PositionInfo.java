package com.daishaowen.test.sousuofujinderen;

public class PositionInfo {
    private String key;

    //经度
    private Double longitude;

    //纬度
    private Double latitude;

    public PositionInfo(String key, Double longitude, Double latitude) {
        this.key = key;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return longitude;
    }

    public void setLatitude(Double longitude) {
        this.longitude = longitude;
    }
}
