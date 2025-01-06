package com.weather_api.WeatherApi.models;

public class FailedCityPair {
    String city;
    String reason;


    public FailedCityPair(){};

    public FailedCityPair(String city, String reason) {
        this.city = city;
        this.reason = reason;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
