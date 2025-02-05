package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Location (
        @JsonAlias("lat")
        String lat, @JsonAlias("lon") String lng){};
