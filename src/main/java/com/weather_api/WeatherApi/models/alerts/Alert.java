package com.weather_api.WeatherApi.models.alerts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.weather_api.WeatherApi.models.users.User;
import jakarta.persistence.*;

@Entity
public class Alert {

    @Id
    @GeneratedValue
    private Long id;

    private String lat;
    private String lng;

    @Enumerated(EnumType.STRING)
    private AlertType alertType;    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }
}
