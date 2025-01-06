package com.weather_api.WeatherApi.controllers;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather_api.WeatherApi.WeatherServiceException;
import com.weather_api.WeatherApi.models.*;
import com.weather_api.WeatherApi.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/weather/v1/")
public class WeatherController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper om;

    @Value("${weather.api-key}")
    String weatherApiKey;

    @Value("${geocoding.api-key}")
    String geocodingApikey;

    @Autowired
    WeatherService weatherService;

    public  void cityValidation(String city){

        if(city==null || city.trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"City name cannot be null or empty");

        if (!city.trim().matches("^[a-zA-Z ]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City name '" + city.trim() + "' must contain only letters and spaces.");
        }
    }



    @GetMapping("/today/{city}")
    public CityWeatherResponse getCityWeather(@PathVariable("city") String city) {

        try{

            cityValidation(city);

            WeatherDTO weatherDTO = weatherService.getCityWeather(city);

            if(weatherDTO==null)
                return null;
//            throw invalid input exception;

            return new CityWeatherResponse("success",weatherDTO);

        }

        catch (HttpClientErrorException clientErrorException){

            return new CityWeatherResponse("No City found with the given name.");
        }


        catch (Exception e){
            return new CityWeatherResponse(e.getMessage());
        }

    }



    @GetMapping("/today/cities/{cities}")
    public CitiesWeatherResponse getCitiesWeather(@PathVariable("cities") String citiesString) {

        if(citiesString == null || citiesString.trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cities value cannot not be empty");

        String[] cities = citiesString.split(",");

        for(String city: cities){
            cityValidation(city);
        }


        return new CitiesWeatherResponse("success",weatherService.getCitiesWeather(Arrays.asList(cities)));
    }

    @GetMapping("/forecast/{city}")
    public ForecastWeatherResponse getForecastByCity(@PathVariable("city") String city, @RequestParam(value = "days",defaultValue = "5") int days){

        try {

            cityValidation(city);

            if(days<1 || days>5)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"days value must be between 1 and 5");

            city = city.trim();

            ForecastWeatherList forecastWeatherList = weatherService.getCityForecastedWeather(city);

            ForecastWeatherResponse forecastWeatherResponse = new ForecastWeatherResponse(forecastWeatherList.list());

            int maxEntries = days*8;

            if(forecastWeatherResponse.getForecastWeatherData().size()>maxEntries)
                forecastWeatherResponse.setForecastWeatherData(forecastWeatherResponse.getForecastWeatherData().subList(0,maxEntries));


            forecastWeatherResponse.setResultMessage("success");

            return forecastWeatherResponse;

        }
        catch (HttpClientErrorException clientErrorException){

            return new ForecastWeatherResponse("No City found with the given name.");
        }


        catch (Exception e){
            System.out.println(e.getMessage());
            return new ForecastWeatherResponse(e.getMessage());
        }

    }
}
