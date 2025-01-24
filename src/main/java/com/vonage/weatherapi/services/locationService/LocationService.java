package com.vonage.weatherapi.services.locationService;

import com.vonage.weatherapi.models.alerts.Location;


public interface LocationService {

    Location getLocationCooordinates(String city);
}
