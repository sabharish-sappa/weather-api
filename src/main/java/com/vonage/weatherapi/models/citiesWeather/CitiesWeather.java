package com.vonage.weatherapi.models.citiesWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vonage.weatherapi.models.cityWeather.CityWeatherPair;

import java.util.List;

public class CitiesWeather {
    List<CityWeatherPair> successfulCities;
    List<FailedCityPair>failedCities;

    public CitiesWeather(){};

    public CitiesWeather(List<CityWeatherPair> successfulCities, List<FailedCityPair> failedCities) {
        this.successfulCities = successfulCities;
        this.failedCities = failedCities;
    }

    @JsonProperty("successful_cities")
    public List<CityWeatherPair> getSuccessfulCities() {
        return successfulCities;
    }

    public void setSuccessfulCities(List<CityWeatherPair> weatherData) {
        this.successfulCities = weatherData;
    }

    @JsonProperty("failed_cities")
    public List<FailedCityPair> getFailedCities() {
        return failedCities;
    }

    public void setFailedCities(List<FailedCityPair> failedCities) {
        this.failedCities = failedCities;
    }
}
