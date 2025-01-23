package com.weather_api.WeatherApi.services.locationService;

import com.weather_api.WeatherApi.models.alerts.Location;
import org.springframework.stereotype.Service;


public interface LocationService {

    Location getLocationCooordinates(String city);
}
