package com.weather_api.WeatherApi.models;

public class WeatherResponse {


    String resultMessage;
    String city;
    Weather weather;

    public WeatherResponse(String city, String resultMessage, Weather weather) {

        this.resultMessage = resultMessage;
        this.weather = weather;
        this.city = city;
    }

    public WeatherResponse(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public WeatherResponse(){}


    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }



    public void setCity(String city) {
        this.city = city;
    }


}

