package com.weather_api.WeatherApi.services;

import com.weather_api.WeatherApi.models.Location;

public interface LocationService {

    Location getLocationCooordinates(String city);
}
