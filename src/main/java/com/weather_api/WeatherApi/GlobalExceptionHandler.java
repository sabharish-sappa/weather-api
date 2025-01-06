package com.weather_api.WeatherApi;

import com.weather_api.WeatherApi.services.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<String> handleWeatherServiceException(WeatherServiceException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> handleGenericException(Exception ex){
        return new ResponseEntity<>("An unexpected error occured : "+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
