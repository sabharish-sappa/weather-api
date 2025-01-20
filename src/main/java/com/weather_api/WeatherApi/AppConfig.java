package com.weather_api.WeatherApi;


import com.weather_api.WeatherApi.models.alerts.AlertRequest;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.alertService.AlertService;
import com.weather_api.WeatherApi.services.userService.UserService;
import com.weather_api.WeatherApi.services.weatherService.WeatherService;
import com.weather_api.WeatherApi.services.weatherService.WeatherServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {



        @Bean
        public AlertService alertService(AlertGenerator alertGenerator) {
            return new AlertService();
        }

        @Bean
        public AlertGenerator alertGenerator(WeatherService weatherService) {
            return new AlertGenerator();
        }

        @Bean
        public WeatherService weatherService(RestTemplate restTemplate) {
            return new WeatherServiceImpl();
        }





}
