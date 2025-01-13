package com.weather_api.WeatherApi.models.CityWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CityWeather(CityWeatherMain main, String visibility, CityWeatherSys sys ){}


