package com.weather_api.WeatherApi.services;

import com.weather_api.WeatherApi.models.CitiesWeather;
import com.weather_api.WeatherApi.models.ForecastWeatherList;
import com.weather_api.WeatherApi.models.CityWeatherPair;
import com.weather_api.WeatherApi.models.WeatherDTO;

import java.util.List;

public interface WeatherService {

    WeatherDTO getCityWeather(String city);
    CitiesWeather getCitiesWeather(List<String>cities);
    ForecastWeatherList getCityForecastedWeather(String city);
}
