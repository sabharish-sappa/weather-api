package com.weather_api.WeatherApi.models.cityWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CityWeatherMain(String temp, String temp_min, String temp_max, String pressure, String humidity, String sea_level){}

