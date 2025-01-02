package com.weather_api.WeatherApi.models;

public class WeatherResponse {


    String result_message;
    String city;
    Weather weather;

    public WeatherResponse(String city, String result_message, Weather weather) {

        this.result_message = result_message;
        this.weather = weather;
        this.city = city;
    }

    public WeatherResponse(String resultMessage) {
        this.result_message = resultMessage;
    }

    public WeatherResponse(){}


    public String getResultMessage() {
        return result_message;
    }

    public void setResultMessage(String resultMessage) {
        this.result_message = resultMessage;
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

