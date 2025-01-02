package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastResponse {
    @JsonAlias("list")
    List<ForecastWeather> list;

    public List<ForecastWeather> getList() {
        return list;
    }

    public void setList(List<ForecastWeather> list) {
        this.list = list;
    }

    public ForecastResponse(List<ForecastWeather> list) {
        this.list = list;
    }

    public ForecastResponse(){};

}



