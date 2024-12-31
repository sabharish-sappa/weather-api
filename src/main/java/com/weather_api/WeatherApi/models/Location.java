package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Location (
        @JsonProperty("lat")
        String lat, @JsonProperty("lon") String lng){};
