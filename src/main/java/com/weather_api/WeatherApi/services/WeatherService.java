package com.weather_api.WeatherApi.services;

import com.weather_api.WeatherApi.models.CitiesWeather.CitiesWeather;
import com.weather_api.WeatherApi.models.ForecastWeather.ForecastWeatherList;
import com.weather_api.WeatherApi.models.CityWeather.WeatherDTO;

import java.util.List;

public interface WeatherService {

    WeatherDTO getCityWeather(String city);
    CitiesWeather getCitiesWeather(List<String>cities);
    ForecastWeatherList getCityForecastedWeather(String city);
}
