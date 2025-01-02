package com.weather_api.WeatherApi.Controllers;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather_api.WeatherApi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Soundbank;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather/v1/")
public class WeatherController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper om;

    @Value("${weather.api-key}")
    String weatherApiKey;

    @Value("${geocoding.api-key}")
    String geocodingApikey;

    @GetMapping("/today/{city}")
    public WeatherResponse getCityWeather(@PathVariable("city") String city) throws URISyntaxException {
        WeatherResponse weatherResponse = new WeatherResponse();

        try{

            Location locationCordinates = getLocation(city);

            if(locationCordinates==null)
                throw  new HttpClientErrorException(HttpStatus.NOT_FOUND,"City not found...");

            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + weatherApiKey;

            String locationBasedUrl = "https://api.openweathermap.org/data/2.5/weather?lat="+locationCordinates.lat()+"&lon="+locationCordinates.lng()+"&units=metric&appid="+weatherApiKey;


            Weather weather = restTemplate.getForObject(locationBasedUrl, Weather.class);
            System.out.println(weather);
            weatherResponse.setWeather(weather);
            weatherResponse.setCity(city);
            weatherResponse.setResultMessage("Success");


            return weatherResponse;
        }

        catch (HttpClientErrorException clientErrorException){
            weatherResponse.setCity(city);

            return new WeatherResponse(city,"City Not Found...",null);
        }


        catch (Exception e){
            System.out.println(e);

            weatherResponse.setCity(city);
            weatherResponse.setResultMessage(e.getMessage());

            return new WeatherResponse(city, e.getMessage(),null);
        }
    }



    @GetMapping("/today/cities/{cities}")
    public List<WeatherResponse> getCitiesWeather(@PathVariable("cities") String citiesString) throws URISyntaxException {


        String[] cities = citiesString.split(",");
        List<WeatherResponse> weatherResponses = new ArrayList<>();
        for(String city :cities){
            weatherResponses.add(getCityWeather(city));
        }

        return weatherResponses;
    }

    @GetMapping("/forecast/{city}")
    public ForecastResponse getForecastByCity(@PathVariable("city") String city, @RequestParam("days") int days){

        try {
            Location locationCordinates = getLocation(city);
            if (locationCordinates == null)
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found...");

            System.out.println("from the function");
            String url = "https://api.openweathermap.org/data/2.5/forecast?lat="+locationCordinates.lat()+"&lon="+locationCordinates.lng()+"&appid="+weatherApiKey;


            ForecastResponse forecastResponse =  restTemplate.getForObject(url, ForecastResponse.class);

            if(forecastResponse == null || forecastResponse.getList()==null)
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT,"No forecast data found...");


            int maxEntries = days*8;
            if(forecastResponse.getList().size()>maxEntries)
            {
                forecastResponse.setList(forecastResponse.getList().subList(0,maxEntries));
            }

            System.out.println(forecastResponse.getList().size());


            return forecastResponse;




        }
        catch (HttpClientErrorException clientErrorException){

            System.out.println(clientErrorException.getMessage());

            return null;

        }


        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }




    }


    public Location getLocation(String city){
        String url = "https://geocode.maps.co/search?q="+city+"&api_key="+geocodingApikey;

//        List<Location> locationList = restTemplate.getForObject(url, List.class);

        ResponseEntity<List<Location>> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Location>>() {}

        );

        List<Location> locationList = responseEntity.getBody();

        // Check and return the 0th object safely
        if (locationList != null && !locationList.isEmpty()) {
            return locationList.get(0); // Return the 0th address
        }

        throw new IllegalArgumentException("No locations found for the given city name.");
    }


}
