package com.weather_api.WeatherApi.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"result_message","city","weather"})
public class WeatherResponse {


    String resultMessage;
    String city;
    WeatherDTO weatherDTO;

    public WeatherResponse(String city, String resultMessage, Weather weather) {

        this.resultMessage = resultMessage;
        this.weatherDTO = new WeatherDTO(weather);
        this.city = city;
    }

    public WeatherResponse(String resultMessage, String city){
        this.resultMessage = resultMessage;
        this.city = city;
    }



    public WeatherResponse( String city,String resultMessage, WeatherDTO weatherDTO) {
        this.resultMessage = resultMessage;
        this.city = city;
        this.weatherDTO = weatherDTO;
    }

    public WeatherResponse(String result_message) {
        this.resultMessage = result_message;
    }

    public WeatherResponse(){}

    @JsonProperty("result_message")
    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public WeatherDTO getWeather() {
        return weatherDTO;
    }

    public void setWeather(WeatherDTO weatherDTO) {
        this.weatherDTO = weatherDTO;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}

