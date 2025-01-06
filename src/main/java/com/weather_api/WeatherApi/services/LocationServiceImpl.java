package com.weather_api.WeatherApi.services;

import com.weather_api.WeatherApi.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class LocationServiceImpl implements LocationService{

    @Value("${geocoding.api-key}")
    String geocodingApikey;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Location getLocationCooordinates(String city) {

        String url = "https://geocode.maps.co/search?q="+city+"&api_key="+geocodingApikey;


        ResponseEntity<List<Location>> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Location>>() {}

        );

        List<Location> locationList = responseEntity.getBody();

        if (locationList != null && !locationList.isEmpty()) {
            return locationList.get(0); // Return the 0th address
        }

        return null;
    }
}
