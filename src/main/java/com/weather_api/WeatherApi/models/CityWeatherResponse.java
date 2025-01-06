package com.weather_api.WeatherApi.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"result_message","result_code","city","weather"})
public class CityWeatherResponse {


    String resultMessage;
    WeatherDTO weatherDTO;

    public CityWeatherResponse(String city, String resultMessage, Weather weather) {

        this.resultMessage = resultMessage;
        this.weatherDTO = new WeatherDTO(weather);
    }

    public CityWeatherResponse(String resultMessage){
        this.resultMessage = resultMessage;
    }

    public CityWeatherResponse(String resultMessage, WeatherDTO weatherDTO) {
        this.resultMessage = resultMessage;
        this.weatherDTO = weatherDTO;
    }

    public CityWeatherResponse(String resultMessage, Weather weather) {
        this.resultMessage = resultMessage;
        this.weatherDTO = new WeatherDTO(weather);
    }




    public CityWeatherResponse(){}

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



    @JsonProperty("weather")
    public WeatherDTO getWeatherDTO() {
        return weatherDTO;
    }

    public void setWeatherDTO(WeatherDTO weatherDTO) {
        this.weatherDTO = weatherDTO;
    }
}

