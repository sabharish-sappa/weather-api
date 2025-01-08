package com.weather_api.WeatherApi.exceptions;

import com.weather_api.WeatherApi.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WeatherServiceException.class)
    public ResponseEntity<Response> handleWeatherServiceException(WeatherServiceException ex){
        return new ResponseEntity<>(new Response(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception ex){
        return new ResponseEntity<>(new Response("An unexpected error occured : "+ex.getMessage()),HttpStatus.BAD_REQUEST);

    }

}
