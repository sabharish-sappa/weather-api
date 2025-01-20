package com.weather_api.WeatherApi.services.weatherService;

import com.weather_api.WeatherApi.exceptions.WeatherServiceException;
import com.weather_api.WeatherApi.models.alerts.Location;
import com.weather_api.WeatherApi.models.citiesWeather.CitiesWeather;
import com.weather_api.WeatherApi.models.citiesWeather.FailedCityPair;
import com.weather_api.WeatherApi.models.cityWeather.CityWeatherPair;
import com.weather_api.WeatherApi.models.cityWeather.CityWeather;
import com.weather_api.WeatherApi.models.cityWeather.CityWeatherDTO;
import com.weather_api.WeatherApi.models.forecastWeather.ForecastWeatherList;
import com.weather_api.WeatherApi.services.locationService.LocationService;
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
