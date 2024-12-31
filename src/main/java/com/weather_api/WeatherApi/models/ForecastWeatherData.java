package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastWeatherData(@JsonProperty("list") List<ForecastWeather>forecastData) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record ForecastWeather(@JsonProperty("dt_txt") String date, Main main, String visibility ){}

