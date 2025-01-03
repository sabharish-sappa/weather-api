package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastMain(
        @JsonAlias("humidity") int humidity,
        @JsonAlias("temp_min") double minTemp,
        @JsonAlias("temp_max") double maxTemp,
        @JsonAlias("pressure") int pressure,
        @JsonAlias("sea_level") int seaLevel
) {}