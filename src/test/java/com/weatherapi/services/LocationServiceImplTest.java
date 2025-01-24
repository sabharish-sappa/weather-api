package com.weatherapi.services;

import com.vonage.weatherapi.models.alerts.Location;
import com.vonage.weatherapi.services.locationService.LocationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class LocationServiceImplTest {

    @Autowired
    private LocationServiceImpl locationService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${geocoding.api-key}")
    private String geocodingApiKey;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetLocationCooordinates_Success() {
        String city = "bengaluru";
        String expectedUrl = "https://geocode.maps.co/search?q=" + city + "&api_key=" + geocodingApiKey;

        Location mockLocation = new Location("12.9767936","77.590082");

        List<Location> mockLocationList = Collections.singletonList(mockLocation);

        ResponseEntity<List<Location>> mockResponseEntity = ResponseEntity.ok(mockLocationList);
        when(restTemplate.exchange(
                eq(expectedUrl),
                eq(HttpMethod.GET),
                eq(null),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponseEntity);

        Location result = locationService.getLocationCooordinates(city);

        assertNotNull(result);
        assertEquals("12.9767936", result.lat());
        assertEquals("77.590082", result.lng());

    }

    @Test
    void testGetLocationCoordinates_CityNotFound() {

        String city = "bengaluruu";
        String expectedUrl = "https://geocode.maps.co/search?q=" + city + "&api_key=" + geocodingApiKey;

        when(restTemplate.exchange(
                eq(expectedUrl),
                eq(HttpMethod.GET),
                eq(null),
                any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

        Location result = locationService.getLocationCooordinates(city);

        assertNull(result);
    }


}