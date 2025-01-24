package com.vonage.weatherapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonage.weatherapi.exceptions.WeatherServiceException;
import com.vonage.weatherapi.models.citiesWeather.CitiesWeatherResponse;
import com.vonage.weatherapi.models.cityWeather.CityWeatherResponse;
import com.vonage.weatherapi.dto.CityWeatherDTO;
import com.vonage.weatherapi.models.forecastWeather.ForecastWeatherList;
import com.vonage.weatherapi.models.forecastWeather.ForecastWeatherResponse;
import com.vonage.weatherapi.services.weatherService.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/weather/")
public class WeatherController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper om;

    @Autowired
    WeatherService weatherService;

    public  void cityValidation(String city){


        if(city==null || city.trim().isEmpty())
            throw new WeatherServiceException("City name cannot be null or empty");

        if (!city.trim().matches("^[a-zA-Z ]+$")) {
            throw new WeatherServiceException("City name '" + city.trim() + "' must contain only letters and spaces.");
        }
    }



    @GetMapping("/today")
    public CityWeatherResponse getCityWeather(@RequestParam(value = "city",required = false) String city) {

        try{

            cityValidation(city);

            CityWeatherDTO cityWeatherDTO = weatherService.getCityWeather(city);

            if(cityWeatherDTO ==null)
                return null;
//            throw invalid input exception;

            return new CityWeatherResponse("success", cityWeatherDTO);

        }

        catch (HttpClientErrorException clientErrorException){

            throw new WeatherServiceException("No City found with the given name.");
        }

        catch (ResponseStatusException ex){
            throw new ResponseStatusException(ex.getStatusCode(),ex.getMessage());
        }


        catch (Exception e){
            throw  new WeatherServiceException(e.getMessage());
        }

    }



    @GetMapping("/today/multiple")
    public CitiesWeatherResponse getCitiesWeather(@RequestParam(value = "cities",required = false) String citiesString) {

        if(citiesString == null || citiesString.trim().isEmpty())
            throw new WeatherServiceException("Cities value cannot not be empty");

        String[] cities = citiesString.split(",");

        for(String city: cities){
            cityValidation(city);
        }

        return new CitiesWeatherResponse("success",weatherService.getCitiesWeather(Arrays.asList(cities)));
    }

    @GetMapping("/forecast")
    public ForecastWeatherResponse getForecastByCity(@RequestParam(value = "city", required = false)String city, @RequestParam(value = "days",required = false) Integer days){

        try {

            cityValidation(city);
            city = city.trim();


            if(days==null)
                throw new WeatherServiceException("Query Parameter days is required.");

            if(days<1 || days>5)
                throw new WeatherServiceException("Days value must be between 1 and 5");


            ForecastWeatherList forecastWeatherList = weatherService.getCityForecastedWeather(city);

            ForecastWeatherResponse forecastWeatherResponse = new ForecastWeatherResponse(forecastWeatherList.list());

            int maxEntries = days*8;

            if(forecastWeatherResponse.getForecastWeatherData().size()>maxEntries)
                forecastWeatherResponse.setForecastWeatherData(forecastWeatherResponse.getForecastWeatherData().subList(0,maxEntries));


            forecastWeatherResponse.setResultMessage("success");

            return forecastWeatherResponse;

        }
        catch (HttpClientErrorException clientErrorException){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No City found with the given name.");
        }


        catch (Exception e){

            throw new WeatherServiceException(e.getMessage());
        }

    }
}
