package com.vonage.weatherapi.services.weatherService;

import com.vonage.weatherapi.models.alerts.Location;
import com.vonage.weatherapi.models.citiesWeather.CitiesWeather;
import com.vonage.weatherapi.models.forecastWeather.ForecastWeatherList;
import com.vonage.weatherapi.dto.CityWeatherDTO;

import java.util.List;

public interface WeatherService {

    CityWeatherDTO getCityWeather(String city);
    CitiesWeather getCitiesWeather(List<String>cities);
    ForecastWeatherList getCityForecastedWeather(String city);
    CityWeatherDTO getParticularLocationWeather(Location location);

}
