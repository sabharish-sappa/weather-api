package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"result_message","successful_cities","failed_cities"})
public class CitiesWeatherResponse extends Response {
//    String resultMessage;
    CitiesWeather citiesWeather;

    public CitiesWeatherResponse(){};

    public CitiesWeatherResponse(String resultMessage, CitiesWeather citiesWeather){
        this.citiesWeather = citiesWeather;
        this.resultMessage = resultMessage;
    }
//
//    public String getResultMessage() {
//        return resultMessage;
//    }
//
//    @JsonProperty("result_message")
//    public void setResultMessage(String resultMessage) {
//        this.resultMessage = resultMessage;
//    }

    @JsonProperty("weather_data")
    public CitiesWeather getCitiesWeather() {
        return citiesWeather;
    }

    public void setCitiesWeather(CitiesWeather citiesWeather) {
        this.citiesWeather = citiesWeather;
    }

    //    public CitiesWeatherResponse(String resultMessage, List<CityWeatherPair> successfulCities){
//        this.successfulCities = successfulCities;
//        this.resultMessage = resultMessage;
//    }
//
//    public CitiesWeatherResponse(String resultMessage, CitiesWeather citiesWeather){
//        this.resultMessage = resultMessage;
//        this.successfulCities = citiesWeather.getSuccessfulCities();
//        this.failedCities = citiesWeather.getFailedCities();
//
//        if(this.failedCities.size()==0)
//            this.failedCities=null;
//    }
//
//
//
//    @JsonProperty("result_message")
//    public String getResultMessage() {
//        return resultMessage;
//    }
//
//
//    public void setResultMessage(String resultMessage) {
//        this.resultMessage = resultMessage;
//    }
//
//
//    @JsonProperty("successful_cities")
//    public List<CityWeatherPair> getSuccessfulCities() {
//        return successfulCities;
//    }
//
//    public void setSuccessfulCities(List<CityWeatherPair> weatherData) {
//        this.successfulCities = weatherData;
//    }
//
//    @JsonProperty("failed_cities")
//    public List<FailedCityPair> getFailedCities() {
//        return failedCities;
//    }
//
//    public void setFailedCities(List<FailedCityPair> failedCities) {
//        this.failedCities = failedCities;
//    }
}
