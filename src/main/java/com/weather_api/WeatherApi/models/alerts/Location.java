package com.weather_api.WeatherApi.models.alerts;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Location (
        @JsonAlias("lat")
        String lat, @JsonAlias("lon") String lng){};
