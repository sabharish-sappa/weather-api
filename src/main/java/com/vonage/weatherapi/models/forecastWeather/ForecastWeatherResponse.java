package com.vonage.weatherapi.models.forecastWeather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vonage.weatherapi.dto.ForecastWeatherDTO;
import com.vonage.weatherapi.models.citiesWeather.DateForecastedWeatherPair;
import com.vonage.weatherapi.models.Response;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"result_message","result_code","city","result_count","forecast_data"})
public class ForecastWeatherResponse extends Response {

//    String resultMessage;
    List<DateForecastedWeatherPair> forecastWeatherData;

    public ForecastWeatherResponse(){}


    public ForecastWeatherResponse(String resultMessage){
        this.resultMessage = resultMessage;
    }

    public ForecastWeatherResponse(String resultMessage,List<DateForecastedWeatherPair> forecastWeatherData) {
        this.resultMessage = resultMessage;
        this.forecastWeatherData = forecastWeatherData;
    }


    public ForecastWeatherResponse(List<ForecastWeather> forecastWeatherData) {

        this.forecastWeatherData = new ArrayList<>();
       forecastWeatherData.forEach(forecastWeather -> this.forecastWeatherData.add(new DateForecastedWeatherPair(
               forecastWeather.date(),new ForecastWeatherDTO(forecastWeather))));
    }

    @JsonProperty("forecast_data")
    public List<DateForecastedWeatherPair> getForecastWeatherData() {
        return forecastWeatherData;
    }

    public void setForecastWeatherData(List<DateForecastedWeatherPair> forecastWeatherData) {
        this.forecastWeatherData = forecastWeatherData;
    }

//    @JsonProperty("result_message")
//    public String getResultMessage() {
//        return resultMessage;
//    }
//
//    public void setResultMessage(String resultMessage) {
//        this.resultMessage = resultMessage;
//    }

}



