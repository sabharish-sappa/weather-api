package com.weather_api.WeatherApi.models.cityWeather;

public class CityWeatherPair {

    String city;
    CityWeatherDTO weather;

    public CityWeatherPair(){};

    public CityWeatherPair(String city, CityWeatherDTO weather) {
        this.city = city;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CityWeatherDTO getWeather() {
        return weather;
    }

    public void setWeather(CityWeatherDTO weather) {
        this.weather = weather;
    }
}
