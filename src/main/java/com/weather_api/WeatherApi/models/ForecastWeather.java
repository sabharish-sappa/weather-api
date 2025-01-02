package com.weather_api.WeatherApi.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastWeather(
        @JsonAlias("main") ForecastMain main,
        @JsonAlias("visibility") int visibility,
        @JsonAlias("dt_txt") String date
) {}