package com.weather_api.WeatherApi.controllers;

import com.weather_api.WeatherApi.WeatherServiceException;
import com.weather_api.WeatherApi.models.*;
import com.weather_api.WeatherApi.services.WeatherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class WeatherControllerTest {

    @InjectMocks
    WeatherController weatherController;
    @Mock
    WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        weatherController.weatherService = weatherService;

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void cityValidation() {


    }

    @Test
    void getCityWeather_ValidCity_ReturnsWeatherResponse() {
        Weather mockWeather1 = new Weather(new WeatherMain("15", "10", "20", "1013", "60", "1013"), "10000", new WeatherSys("1704441600", "1704484800"));
        WeatherDTO mockWeatherDTO = new WeatherDTO(mockWeather1);

        when(weatherService.getCityWeather("bengaluru")).thenReturn(mockWeatherDTO);

        CityWeatherResponse response = weatherController.getCityWeather("bengaluru");

        assertNotNull(response);
        assertEquals("success", response.getResultMessage());
        assertEquals(mockWeatherDTO, response.getWeatherDTO());
    }

    @Test
    void getCityWeather_InvalidCity_ThrowsException() {

        when(weatherService.getCityWeather("invalidcity"))
                .thenThrow(new WeatherServiceException("No City found with the given name."));

        CityWeatherResponse response = weatherController.getCityWeather("invalidcity");

        assertNotNull(response);
        assertEquals("No City found with the given name.", response.getResultMessage());
        assertNull(response.getWeatherDTO());
    }



    //    needs to fix this
    @Test
    void getCityWeather_NullOrEmptyCity_ThrowsBadRequest() {

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> weatherController.getCityWeather(null));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getReason());
        assertEquals("City name cannot be null or empty", exception.getReason());

        exception = assertThrows(ResponseStatusException.class,
                () -> weatherController.getCityWeather(" "));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getReason());
        assertEquals("City name cannot be null or empty", exception.getReason());
    }

    @Test
    void getCitiesWeather_ValidCities_ReturnsWeatherResponse() {
        List<String> cities = List.of("bengaluru", "hyderabad");

        Weather mockWeather1 = new Weather(new WeatherMain("15", "10", "20", "1013", "60", "1013"), "10000", new WeatherSys("1704441600", "1704484800"));
        Weather mockWeather2 = new Weather(new WeatherMain("25", "15", "30", "1013", "80", "1013"), "10000", new WeatherSys("1754645441600", "6456532432"));

        CitiesWeather mockCitiesWeather = new CitiesWeather(
                List.of(
                        new CityWeatherPair("bengaluru", new WeatherDTO(mockWeather1)),
                        new CityWeatherPair("delhi", new WeatherDTO(mockWeather2))
                ),
                List.of()
        );
        when(weatherService.getCitiesWeather(cities)).thenReturn(mockCitiesWeather);

        CitiesWeatherResponse response = weatherController.getCitiesWeather("bengaluru,hyderabad");

        assertNotNull(response);
        assertEquals("success", response.getResultMessage());
        assertEquals(mockCitiesWeather, response.getCitiesWeather());
    }

    @Test
    void getCitiesWeather_InvalidCities_ThrowsBadRequest() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> weatherController.getCitiesWeather(null));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Cities value cannot not be empty", exception.getReason());

        exception = assertThrows(ResponseStatusException.class,
                () -> weatherController.getCitiesWeather(" "));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Cities value cannot not be empty", exception.getReason());
    }


    @Test
    void testGetForecastByCity() {
    }
}
