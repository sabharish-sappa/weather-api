package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"result_message","city","result_count","forecast_data"})
public class ForecastWeatherResponse {

    String resultMessage;
    String city;
    String size;
    List<ForecastWetherDTO> forecastWeatherData;

    public ForecastWeatherResponse(){}

    public ForecastWeatherResponse(String resultMessage, String city,String size){
        this.resultMessage = resultMessage;
        this.city = city;
        this.size= size;
    }

    public ForecastWeatherResponse(String resultMessage, String city, String size, List<ForecastWetherDTO> forecastWeatherData) {
        this.resultMessage = resultMessage;
        this.city = city;
        this.size = size;
        this.forecastWeatherData = forecastWeatherData;
    }

    public ForecastWeatherResponse(String resultMessage, String city, List<ForecastWetherDTO> forecastWeatherData) {
        this.resultMessage = resultMessage;
        this.city = city;
        this.forecastWeatherData = forecastWeatherData;
    }

    public ForecastWeatherResponse(List<ForecastWeather> forecastWeatherData) {

        this.forecastWeatherData = new ArrayList<>();
       forecastWeatherData.forEach(forecastWeather -> this.forecastWeatherData.add(new ForecastWetherDTO(forecastWeather)));
    }

    @JsonProperty("forecast_data")
    public List<ForecastWetherDTO> getForecastWeatherData() {
        return forecastWeatherData;
    }

    public void setForecastWeatherData(List<ForecastWetherDTO> forecastWeatherData) {
        this.forecastWeatherData = forecastWeatherData;
    }

    @JsonProperty("result_message")
    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("result_count")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}



