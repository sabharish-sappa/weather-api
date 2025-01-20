package com.weather_api.WeatherApi.services.locationService;

import com.weather_api.WeatherApi.models.alerts.Location;

public interface LocationService {

    Location getLocationCooordinates(String city);
}
