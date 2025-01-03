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
import java.util.InputMismatchException;
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
            weatherResponse.setResultMessage("Success");
            weatherResponse.setCity(city);
            weatherResponse.setWeather(new WeatherDTO(weather));



            return weatherResponse;
        }

        catch (HttpClientErrorException clientErrorException){
            weatherResponse.setCity(city);
            return new WeatherResponse(clientErrorException.getMessage(),city);
        }


        catch (Exception e){
            System.out.println(e);
            return new WeatherResponse(e.getMessage(),city);
        }
    }



    @GetMapping("/today/cities/{cities}")
    public List<WeatherResponse> getCitiesWeather(@PathVariable("cities") String citiesString) throws URISyntaxException {


        String[] cities = citiesString.split(",");
        List<WeatherResponse> weatherResponses = new ArrayList<>();

        for(String city :cities){
            System.out.println("Making call to get City Weather for - "+city);
            WeatherResponse wr = getCityWeather(city);
            weatherResponses.add(wr);
            System.out.println(wr);

        }

        return weatherResponses;
    }

    @GetMapping("/forecast/{city}")
    public ForecastWeatherResponse getForecastByCity(@PathVariable("city") String city, @RequestParam("days") int days){

        try {

            if(days<0)
                throw new InputMismatchException("Days value should be positive...");


            Location locationCordinates = getLocation(city);
            if (locationCordinates == null)
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found...");
            String url = "https://api.openweathermap.org/data/2.5/forecast?lat="+locationCordinates.lat()+"&lon="+locationCordinates.lng()+"&appid="+weatherApiKey;


            ForecastWeatherList forecastWeatherList =  restTemplate.getForObject(url, ForecastWeatherList.class);

            if(forecastWeatherList == null || forecastWeatherList.list()==null)
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT,"No forecast data found...");

            int maxEntries = Math.min(days,5)*8;

            ForecastWeatherResponse forecastWeatherResponse = new ForecastWeatherResponse(forecastWeatherList.list());
            forecastWeatherResponse.setSize(Integer.toString(maxEntries));

            if(forecastWeatherResponse.getForecastWeatherData().size()>maxEntries)
                forecastWeatherResponse.setForecastWeatherData(forecastWeatherResponse.getForecastWeatherData().subList(0,maxEntries));

            if(days>5)
                forecastWeatherResponse.setResultMessage("Success (At max 5 days forecast is only possible)");

            else
                forecastWeatherResponse.setResultMessage("Success");

            forecastWeatherResponse.setCity(city);

            return forecastWeatherResponse;

        }
        catch (HttpClientErrorException clientErrorException){

            System.out.println(clientErrorException.getMessage());
            return new ForecastWeatherResponse(clientErrorException.getMessage(),city,"0");
        }


        catch (Exception e){
            System.out.println(e.getMessage());
            return new ForecastWeatherResponse(e.getMessage(),city,"0");
        }

    }


    public Location getLocation(String city){
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

        throw new IllegalArgumentException("No locations found for the given city name.");
    }


}
