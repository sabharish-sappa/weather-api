package com.weather_api.WeatherApi.controllers;

import com.weather_api.WeatherApi.exceptions.WeatherServiceException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class WeatherControllerTest {

    @InjectMocks
    WeatherController weatherController;
    @Mock
    WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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


        assertThrowsExactly(WeatherServiceException.class,()->{
            weatherController.getCityWeather("invalidcity");
        });
    }



    //    needs to fix this
    @Test
    void getCityWeather_NullOrEmptyCity_ThrowsBadRequest() {

        WeatherServiceException exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getCityWeather(null));
        assertEquals("City name cannot be null or empty", exception.getMessage());

        exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getCityWeather(" "));

        assertEquals("City name cannot be null or empty", exception.getMessage());
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
        WeatherServiceException exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getCitiesWeather(null));
        assertEquals("Cities value cannot not be empty", exception.getMessage());

        exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getCitiesWeather(" "));
        assertEquals("Cities value cannot not be empty", exception.getMessage());
    }

    @Test
    void getForecastByCity_ValidCity_ReturnsForecastResponse() {

        ForecastWeather mockForecastWeather1 = new ForecastWeather(new ForecastMain(12,23.0,34,456,3233),2344,"2025-01-07 12:00:00");
        ForecastWeather mockForecastWeather2 = new ForecastWeather(new ForecastMain(22,23.0,34,456,3233),2344,"2025-01-07 15:00:00");
        ForecastWeather mockForecastWeather3 = new ForecastWeather(new ForecastMain(32,23.0,34,456,3233),2344,"2025-01-07 18:00:00");
        ForecastWeather mockForecastWeather4 = new ForecastWeather(new ForecastMain(42,23.0,34,456,3233),2344,"2025-01-07 21:00:00");
        ForecastWeather mockForecastWeather5 = new ForecastWeather(new ForecastMain(52,23.0,34,456,3233),2344,"2025-01-07 24:00:00");
        ForecastWeather mockForecastWeather6 = new ForecastWeather(new ForecastMain(62,23.0,34,456,3233),2344,"2025-01-08 03:00:00");
        ForecastWeather mockForecastWeather7 = new ForecastWeather(new ForecastMain(72,23.0,34,456,3233),2344,"2025-01-08 06:00:00");
        ForecastWeather mockForecastWeather8 = new ForecastWeather(new ForecastMain(82,23.0,34,456,3233),2344,"2025-01-08 09:00:00");


        ForecastWeatherList mockForecastList = new ForecastWeatherList(List.of(mockForecastWeather1,mockForecastWeather2,mockForecastWeather3,mockForecastWeather4,mockForecastWeather5,mockForecastWeather6,mockForecastWeather7,mockForecastWeather8));
        when(weatherService.getCityForecastedWeather("bengaluru")).thenReturn(mockForecastList);

        ForecastWeatherResponse response = weatherController.getForecastByCity("bengaluru", 1);

        assertNotNull(response);
        assertEquals("success", response.getResultMessage());
        assertTrue(response.getForecastWeatherData().size() <= 1 * 8);
    }

    @Test
    void getForecastByCity_InvalidCity_ThrowsException() {
        // Mocking
        when(weatherService.getCityForecastedWeather("invalidcity"))
                .thenThrow(new WeatherServiceException("No City found with the given name."));


        assertThrowsExactly(WeatherServiceException.class,()->{
            weatherController.getForecastByCity("invalidcity", 3);
        });


    }

    @Test
    void getForecastByCity_InvalidDays_ThrowsBadRequest() {
        WeatherServiceException exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getForecastByCity("bengaluru", 0));
        assertEquals("Days value must be between 1 and 5", exception.getMessage());

        exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getForecastByCity("bengaluru", 6));
        assertEquals("Days value must be between 1 and 5", exception.getMessage());
    }

    @Test
    void getForecastByCity_DaysNotMentioned_ThrowsBadRequest() {
        WeatherServiceException exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getForecastByCity("palasa",null));
        assertEquals("Query Parameter days is required.", exception.getMessage());
    }

    @Test
    void getForecastByCity_CityAndDaysNotMentioned_ThrowsBadRequest() {
        WeatherServiceException exception = assertThrows(WeatherServiceException.class,
                () -> weatherController.getForecastByCity(null,null));
        assertEquals("City name cannot be null or empty", exception.getMessage());
    }


}
