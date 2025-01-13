package com.weather_api.WeatherApi.models.CitiesWeather;

public class FailedCityPair {
    String city;
    String message;


    public FailedCityPair(){};

    public FailedCityPair(String city, String message) {
        this.city = city;
        this.message = message;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String reason) {
        this.message = reason;
    }
}
