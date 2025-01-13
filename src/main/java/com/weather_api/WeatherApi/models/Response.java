package com.weather_api.WeatherApi.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    protected String resultMessage;
//    int statusCode;

    public Response(String resultMessage) {
        this.resultMessage = resultMessage;
    }

//    public Response(String resultMessage, int statusCode) {
//        this.resultMessage = resultMessage;
//        this.statusCode = statusCode;
//    }

    public Response(){};

    @JsonProperty("result_message")
    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

//    @JsonProperty("status_code")
//    public int getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(int statusCode) {
//        this.statusCode = statusCode;
//    }
}
