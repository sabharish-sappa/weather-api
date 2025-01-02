package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastMain(
        @JsonAlias("humidity") int humidity,
        @JsonAlias("temp_min") double min_temp,
        @JsonAlias("temp_max") double max_temp,
        @JsonAlias("pressure") int pressure,
        @JsonAlias("sea_level") int sea_level
) {}