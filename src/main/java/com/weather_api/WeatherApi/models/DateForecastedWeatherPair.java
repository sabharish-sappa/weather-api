package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateForecastedWeatherPair {
    String date;
    ForecastWeatherDTO forecastWeatherDTO;

    public DateForecastedWeatherPair(){};

    public DateForecastedWeatherPair(String date, ForecastWeatherDTO forecastWeatherDTO) {
        this.forecastWeatherDTO = forecastWeatherDTO;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("forecast_weather")
    public ForecastWeatherDTO getForecastWeatherDTO() {
        return forecastWeatherDTO;
    }

    public void setForecastWeatherDTO(ForecastWeatherDTO forecastWeatherDTO) {
        this.forecastWeatherDTO = forecastWeatherDTO;
    }
}
