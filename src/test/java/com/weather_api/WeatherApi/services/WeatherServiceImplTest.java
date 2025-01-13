package com.weather_api.WeatherApi.services;

import com.weather_api.WeatherApi.exceptions.WeatherServiceException;
import com.weather_api.WeatherApi.models.*;
import com.weather_api.WeatherApi.models.CitiesWeather.CitiesWeather;
import com.weather_api.WeatherApi.models.CityWeather.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @InjectMocks
    WeatherServiceImpl weatherService;

    @Value("${weather-api.key}")
    String weatherApiKey;

    @Mock
    LocationService locationService;

    @Mock
    RestTemplate restTemplate;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCityWeather_ValidCity_ReturnsWeatherDTO() {
        String city = "bengaluru";
        Location mockLocation = new Location("12.9767936","77.590082");
        Weather mockWeather = new Weather(new WeatherMain("15", "10", "20", "1013", "60", "0"), "10000", new WeatherSys("1704441600", "1704484800"));

        when(locationService.getLocationCooordinates(city)).thenReturn(mockLocation);
        when(restTemplate.getForObject(
                eq("https://api.openweathermap.org/data/2.5/weather?lat=12.9767936&lon=77.590082&units=metric&appid=" + weatherApiKey),
                eq(Weather.class)
        )).thenReturn(mockWeather);

        WeatherDTO weatherDTO = weatherService.getCityWeather(city);

        // Assert
        assertNotNull(weatherDTO);
        assertEquals("15", weatherDTO.getTemperature());
        verify(locationService).getLocationCooordinates(city);
        verify(restTemplate).getForObject(anyString(), eq(Weather.class));
    }

    @Test
    void getCityWeather_InvalidCity_ThrowsException() {

        String city = "Bengaluruu";
        when(locationService.getLocationCooordinates(city)).thenReturn(null);

        WeatherServiceException exception = assertThrows(WeatherServiceException.class, () -> {
            weatherService.getCityWeather(city);
        });

        assertEquals("No city found with the given name.", exception.getMessage());
        verify(locationService).getLocationCooordinates(city);
        verify(restTemplate, never()).getForObject(anyString(), any());
    }

    @Test
    void getCityWeather_HttpClientErrorException_ThrowsWeatherServiceException() {
        String city = "Bengaluru";
        Location mockLocation = new Location("12.9767936","77.590082");

        when(locationService.getLocationCooordinates(city)).thenReturn(mockLocation);
        when(restTemplate.getForObject(anyString(), eq(Weather.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "No city found with the given name."));

        WeatherServiceException exception = assertThrows(WeatherServiceException.class, () -> {
            weatherService.getCityWeather(city);
        });

        assertTrue(exception.getMessage().contains("No city found with the given name."));
        verify(locationService).getLocationCooordinates(city);
        verify(restTemplate).getForObject(anyString(), eq(Weather.class));
    }


    @Test
    void getCitiesWeather_AllCitiesSuccessful_ReturnsWeatherData() {

        Weather mockWeather1 = new Weather(new WeatherMain("15", "10", "20", "1013", "60", "1013"), "10000", new WeatherSys("1704441600", "1704484800"));
        Weather mockWeather2 = new Weather(new WeatherMain("20", "20", "30", "1193", "80", "1193"), "10000", new WeatherSys("1704441600", "1704484800"));

        Location mockLocation1 = new Location("12.9767936","77.590082");
        Location mockLocation2 = new Location("17.360589","78.4740613");

        CityWeatherPair mockedCityWeatherPair1 = new CityWeatherPair("bengaluru",new WeatherDTO(mockWeather1));
        CityWeatherPair mockedCityWeatherPair2 = new CityWeatherPair("hyderabad",new WeatherDTO(mockWeather2));

        List<CityWeatherPair>mockedSuccessfulCities = new ArrayList<>();
        mockedSuccessfulCities.add(mockedCityWeatherPair1);
        mockedSuccessfulCities.add(mockedCityWeatherPair2);


        CitiesWeather mockCitiesWeather = new CitiesWeather(mockedSuccessfulCities,new ArrayList<>());

        when(locationService.getLocationCooordinates("bengaluru")).thenReturn(mockLocation1);
        when(locationService.getLocationCooordinates("hyderabad")).thenReturn(mockLocation2);

        when(restTemplate.getForObject(
                eq("https://api.openweathermap.org/data/2.5/weather?lat=12.9767936&lon=77.590082&units=metric&appid=" + weatherApiKey),
                eq(Weather.class)
        )).thenReturn(mockWeather1);

        lenient().when(restTemplate.getForObject(
                eq("https://api.openweathermap.org/data/2.5/weather?lat=17.360589&lon=78.4740613&units=metric&appid=" + weatherApiKey),
                eq(Weather.class)
        )).thenReturn(mockWeather2);

        List<String> cities = List.of("bengaluru", "hyderabad");

        CitiesWeather result = weatherService.getCitiesWeather(cities);

        assertNotNull(result);
        assertEquals(2, result.getSuccessfulCities().size());
        assertTrue(result.getFailedCities().isEmpty());
        assertEquals("bengaluru", result.getSuccessfulCities().get(0).getCity());
        assertEquals("15", result.getSuccessfulCities().get(0).getWeather().getTemperature());
    }


@Test
void getCitiesWeather_SomeCitiesFail_ReturnsPartialData() {

    Weather mockWeather1 = new Weather(
            new WeatherMain("15", "10", "20", "1013", "60", "1013"),
            "10000",
            new WeatherSys("1704441600", "1704484800")
    );

    Location locationBengaluru = new Location("12.9767936", "77.590082");


    when(locationService.getLocationCooordinates("bengaluru")).thenReturn(locationBengaluru);


    when(restTemplate.getForObject(
            eq("https://api.openweathermap.org/data/2.5/weather?lat=12.9767936&lon=77.590082&units=metric&appid=" + weatherApiKey),
            eq(Weather.class)
    )).thenReturn(mockWeather1);

    when(locationService.getLocationCooordinates("hyderabaddd"))
            .thenReturn(null);

    List<String> cities = List.of("bengaluru", "hyderabaddd");

    CitiesWeather result = weatherService.getCitiesWeather(cities);

    // Assert successful cities
    assertNotNull(result);
    assertEquals(1, result.getSuccessfulCities().size());
    assertEquals("bengaluru", result.getSuccessfulCities().get(0).getCity());
    assertEquals("15", result.getSuccessfulCities().get(0).getWeather().getTemperature());

    // Assert failed cities
    assertEquals(1, result.getFailedCities().size());
    assertEquals("hyderabaddd", result.getFailedCities().get(0).getCity());
    assertEquals("No city found with the given name.", result.getFailedCities().get(0).getMessage());
}

    @Test
    void getCitiesWeather_AllCitiesFail_ReturnsFailureData() {
        when(locationService.getLocationCooordinates(anyString()))
                .thenReturn(null);

        List<String> cities = List.of("bengaluru", "hyderabad");

        CitiesWeather result = weatherService.getCitiesWeather(cities);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccessfulCities().isEmpty());
        assertEquals(2, result.getFailedCities().size());
        assertEquals("bengaluru", result.getFailedCities().get(0).getCity());
        assertEquals("No city found with the given name.", result.getFailedCities().get(0).getMessage());
    }

    @Test
    void getCitiesWeather_EmptyList_ReturnsEmptyData() {
        List<String> cities = Collections.emptyList();

        CitiesWeather result = weatherService.getCitiesWeather(cities);

        // Assert
        assertNotNull(result);
        assertTrue(result.getSuccessfulCities().isEmpty());
        assertTrue(result.getFailedCities().isEmpty());
    }

    @Test
    void getCitiesWeather_AllCitiesInvalid_ReturnsEmptyResults() {
        when(locationService.getLocationCooordinates(anyString()))
                .thenReturn(null);

        List<String> cities = List.of("invalidcity1", "invalidcity2");

        CitiesWeather result = weatherService.getCitiesWeather(cities);

        // Assertions
        assertNotNull(result, "Result should not be null");
        assertEquals(0, result.getSuccessfulCities().size(), "No city should be successful");
        assertEquals(2, result.getFailedCities().size(), "All cities should fail");

        // Check failed cities
        assertEquals("invalidcity1", result.getFailedCities().get(0).getCity());
        assertEquals("No city found with the given name.", result.getFailedCities().get(0).getMessage());
        assertEquals("invalidcity2", result.getFailedCities().get(1).getCity());
        assertEquals("No city found with the given name.", result.getFailedCities().get(1).getMessage());
    }




}

