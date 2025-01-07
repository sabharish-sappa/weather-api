package com.weather_api.WeatherApi.services;

import com.weather_api.WeatherApi.exceptions.WeatherServiceException;
import com.weather_api.WeatherApi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class WeatherServiceImpl implements WeatherService{

    @Value("${weather.api-key}")
    String weatherApiKey;


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LocationService locationService;

    @Override
    public WeatherDTO getCityWeather(String city) {

        try {

            Location locationCordinates = locationService.getLocationCooordinates(city);

            if (locationCordinates == null)
                throw new WeatherServiceException("No city found with the given name.");

            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + weatherApiKey;

            String locationBasedUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + locationCordinates.lat() + "&lon=" + locationCordinates.lng() + "&units=metric&appid=" + weatherApiKey;

            Weather weather = restTemplate.getForObject(locationBasedUrl, Weather.class);

            return new WeatherDTO(weather);
        }

        catch (HttpClientErrorException httpClientErrorException){
            throw new WeatherServiceException(httpClientErrorException.getMessage(),httpClientErrorException);
        }

        catch (Exception e){
            throw new WeatherServiceException(e.getMessage());
        }

    }



    @Override
    public CitiesWeather getCitiesWeather(List<String> cities) {

        List<CityWeatherPair> successCitiesWeather = new ArrayList<>();
        List<FailedCityPair>failedCityPairs = new ArrayList<>();


        for (String city : cities) {

            try {
                WeatherDTO weatherDTO = getCityWeather(city);
                successCitiesWeather.add(new CityWeatherPair(city, weatherDTO));
            } catch (WeatherServiceException ex) {
               failedCityPairs.add(new FailedCityPair(city,"No city found with the given name."));
            }

        }

        failedCityPairs.forEach(System.out::println);

        return new CitiesWeather(successCitiesWeather,failedCityPairs);

    }

    @Override
    public ForecastWeatherList getCityForecastedWeather(String city) {

        try
        {
            Location locationCordinates = locationService.getLocationCooordinates(city);

            if (locationCordinates == null)
                throw new WeatherServiceException("No city found with the given name.");

            String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + locationCordinates.lat() + "&lon=" + locationCordinates.lng() + "&appid=" + weatherApiKey;

            ForecastWeatherList forecastWeatherList = restTemplate.getForObject(url, ForecastWeatherList.class);
            return forecastWeatherList;
        }

        catch (HttpClientErrorException httpClientErrorException){
            throw new WeatherServiceException(httpClientErrorException.getMessage(),httpClientErrorException);
        }

        catch (Exception e){
            throw new WeatherServiceException(e.getMessage(),e);
        }
    }
}
