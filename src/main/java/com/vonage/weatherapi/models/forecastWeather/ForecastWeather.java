package com.vonage.weatherapi.models.forecastWeather;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastWeather(
        @JsonAlias("main") ForecastMain main,
        @JsonAlias("visibility") int visibility,
        @JsonAlias("dt_txt") String date
) {}