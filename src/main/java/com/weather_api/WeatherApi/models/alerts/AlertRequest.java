package com.weather_api.WeatherApi.models.alerts;

import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.alertService.AlertService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AlertRequest {

    Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
