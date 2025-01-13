package com.weather_api.WeatherApi.models.CityWeather;

public class CityWeatherPair {

    String city;
    WeatherDTO weather;

    public CityWeatherPair(){};

    public CityWeatherPair(String city, WeatherDTO weather) {
        this.city = city;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherDTO getWeather() {
        return weather;
    }

    public void setWeather(WeatherDTO weather) {
        this.weather = weather;
    }
}
