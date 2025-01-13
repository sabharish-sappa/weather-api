package com.weather_api.WeatherApi.models.CityWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Weather (WeatherMain main, String visibility, WeatherSys sys ){}


