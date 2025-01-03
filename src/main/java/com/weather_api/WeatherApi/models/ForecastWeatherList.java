package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ForecastWeatherList(@JsonProperty("list")List<ForecastWeather>list) {
}
