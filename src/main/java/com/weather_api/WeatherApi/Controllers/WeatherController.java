package com.weather_api.WeatherApi.Controllers;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather_api.WeatherApi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

        List<WeatherResponse>weatherResponses = new ArrayList<>();

        for(String city :cities){
            weatherResponses.add(getCityWeather(city));
        }

        return weatherResponses;
    }

//    @GetMapping("/forecast/{city}")
//    public List<ForecastWeatherData> getForecastByCity(@PathVariable("city") String city, @RequestParam("days") int days){
//
//        try {
//
////            if days <=0
//
//
//
//            Location locationCordinates = getLocation(city);
//
//            if (locationCordinates == null)
//                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found...");
//
//            System.out.println("from the function");
//
//            String url = "https://api.openweathermap.org/data/2.5/forecast?lat="+locationCordinates.lat()+"&lon="+locationCordinates.lng()+"&appid="+weatherApiKey;
//
//            List<ForecastWeather> forecastDataFromApi = restTemplate.getForObject(url, List.class);
//            List<ForecastWeatherData> forecastDataOutput = new ArrayList<>();
//
//
//            for(int i =0;i<days*8;i++)
//            {
//                forecastDataOutput.add(forecastDataFromApi.get(i));
//            }
//
//            System.out.println(forecastDataOutput.size());
//
//            return forecastDataOutput;
//
//
//
//
//
//
////            forecastData.forecastData().forEach(System.out::println);
//
//
//
//        }
//        catch (HttpClientErrorException clientErrorException){
//
//            System.out.println(clientErrorException.getMessage());
//
//            return null;
////            weatherResponse.setCity(city);
////
////            return new WeatherResponse(city,"City Not Found...",null);
//        }
//
//
//        catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
////            System.out.println(e);
////
////            weatherResponse.setCity(city);
////            weatherResponse.setResultMessage(e.getMessage());
////
////            return new WeatherResponse(city, e.getMessage(),null);
//        }
//
//
//
////        return null;
//
//    }


    public Location getLocation(String city){
        String url = "https://geocode.maps.co/search?q="+city+"&api_key="+geocodingApikey;

        List<Map<String, Location>> response = restTemplate.getForObject(url,List.class);


//        list of strings
//        take the 0th string
//        convert that strng to location

//list of locations

        if(response.size()==0)
            return null;

        Location location = om.convertValue(response.get(0), Location.class);

        System.out.println(location);
        return location;

    }


}
