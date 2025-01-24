package com.vonage.weatherapi.dto;

public class AlertRequest {

    String lat;
    String lng;
    String alertType;
    Long userId;

    public AlertRequest(){};

    public AlertRequest(String alertType, String lat, String lng, Long userId) {
        this.alertType = alertType;
        this.lat = lat;
        this.lng = lng;
        this.userId = userId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AlertRequest{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", alertType='" + alertType + '\'' +
                ", userId=" + userId + userId.getClass().getName()+
                '}';
    }
}
