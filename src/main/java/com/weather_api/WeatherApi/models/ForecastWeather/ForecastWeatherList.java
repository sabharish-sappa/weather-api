package com.weather_api.WeatherApi.models.ForecastWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ForecastWeatherList(@JsonProperty("list")List<ForecastWeather>list) {
}
