package com.vonage.weatherapi.services.weatherService;

import com.vonage.weatherapi.exceptions.WeatherServiceException;
import com.vonage.weatherapi.models.alerts.Location;
import com.vonage.weatherapi.models.citiesWeather.CitiesWeather;
import com.vonage.weatherapi.models.citiesWeather.FailedCityPair;
import com.vonage.weatherapi.models.cityWeather.CityWeatherPair;
import com.vonage.weatherapi.models.cityWeather.CityWeather;
import com.vonage.weatherapi.dto.CityWeatherDTO;
import com.vonage.weatherapi.models.forecastWeather.ForecastWeatherList;
import com.vonage.weatherapi.services.locationService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class WeatherServiceImpl implements WeatherService{

    @Value("${openweather.api-key}")
    String openweatherApiKey;

    @Value("${openweather.base-url}")
    String openweatherBaseUrl;


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LocationService locationService;

    @Override
    public CityWeatherDTO getCityWeather(String city) {

        try {

            Location locationCordinates = locationService.getLocationCooordinates(city);

            if (locationCordinates == null)
                throw new WeatherServiceException("No city found with the given name.");

            String url = openweatherBaseUrl+"/data/2.5/weather?q=" + city + "&units=metric&appid=" + openweatherApiKey;

            String locationBasedUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + locationCordinates.lat() + "&lon=" + locationCordinates.lng() + "&units=metric&appid=" + openweatherApiKey;

            CityWeather weather = restTemplate.getForObject(locationBasedUrl, CityWeather.class);

            return new CityWeatherDTO(weather);
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
                CityWeatherDTO cityWeatherDTO = getCityWeather(city);
                successCitiesWeather.add(new CityWeatherPair(city, cityWeatherDTO));
            } catch (WeatherServiceException ex) {
               failedCityPairs.add(new FailedCityPair(city,"No city found with the given name."));
            }

        }


        return new CitiesWeather(successCitiesWeather,failedCityPairs);

    }

    @Override
    public ForecastWeatherList getCityForecastedWeather(String city) {

        try
        {
            Location locationCordinates = locationService.getLocationCooordinates(city);

            if (locationCordinates == null)
                throw new WeatherServiceException("No city found with the given name.");

            String url = openweatherBaseUrl+"/data/2.5/forecast?lat=" + locationCordinates.lat() + "&lon=" + locationCordinates.lng() + "&appid=" + openweatherApiKey;

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



    @Override
    public CityWeatherDTO getParticularLocationWeather(Location locationCordinates) {

        try {
            String locationBasedUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + locationCordinates.lat() + "&lon=" + locationCordinates.lng() + "&units=metric&appid=" + openweatherApiKey;

            CityWeather weather = restTemplate.getForObject(locationBasedUrl, CityWeather.class);

            return new CityWeatherDTO(weather);
        }

        catch (HttpClientErrorException httpClientErrorException){
            throw new WeatherServiceException(httpClientErrorException.getMessage(),httpClientErrorException);
        }

        catch (Exception e){
            throw new WeatherServiceException(e.getMessage());
        }

    }
}
