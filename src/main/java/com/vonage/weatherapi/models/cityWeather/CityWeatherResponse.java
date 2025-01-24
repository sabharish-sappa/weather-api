package com.vonage.weatherapi.models.cityWeather;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vonage.weatherapi.dto.CityWeatherDTO;
import com.vonage.weatherapi.models.Response;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"result_message","result_code","city","weather"})
public class CityWeatherResponse  extends Response {


//    String resultMessage;
    CityWeatherDTO cityWeatherDTO;

    public CityWeatherResponse(String city, String resultMessage, CityWeather weather) {

        this.resultMessage = resultMessage;
        this.cityWeatherDTO = new CityWeatherDTO(weather);
    }

    public CityWeatherResponse(String resultMessage){
        this.resultMessage = resultMessage;
    }

    public CityWeatherResponse(String resultMessage, CityWeatherDTO cityWeatherDTO) {
        this.resultMessage = resultMessage;
        this.cityWeatherDTO = cityWeatherDTO;
    }

    public CityWeatherResponse(String resultMessage, CityWeather weather) {
        this.resultMessage = resultMessage;
        this.cityWeatherDTO = new CityWeatherDTO(weather);
    }




    public CityWeatherResponse(){}

//    @JsonProperty("result_message")
//    public String getResultMessage() {
//        return resultMessage;
//    }
//
//    public void setResultMessage(String resultMessage) {
//        this.resultMessage = resultMessage;
//    }

    public CityWeatherDTO getWeather() {
        return cityWeatherDTO;
    }

    public void setWeather(CityWeatherDTO cityWeatherDTO) {
        this.cityWeatherDTO = cityWeatherDTO;
    }



    @JsonProperty("weather")
    public CityWeatherDTO getWeatherDTO() {
        return cityWeatherDTO;
    }

    public void setWeatherDTO(CityWeatherDTO cityWeatherDTO) {
        this.cityWeatherDTO = cityWeatherDTO;
    }
}

