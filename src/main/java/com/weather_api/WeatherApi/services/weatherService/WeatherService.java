package com.weather_api.WeatherApi.services.weatherService;

import com.weather_api.WeatherApi.models.alerts.Location;
import com.weather_api.WeatherApi.models.citiesWeather.CitiesWeather;
import com.weather_api.WeatherApi.models.forecastWeather.ForecastWeatherList;
import com.weather_api.WeatherApi.models.cityWeather.CityWeatherDTO;

import java.util.List;

public interface WeatherService {

    CityWeatherDTO getCityWeather(String city);
    CitiesWeather getCitiesWeather(List<String>cities);
    ForecastWeatherList getCityForecastedWeather(String city);
    CityWeatherDTO getParticularLocationWeather(Location location);

}
