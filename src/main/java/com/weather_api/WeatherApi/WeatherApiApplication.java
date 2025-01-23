package com.weather_api.WeatherApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertRequest;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.alertService.AlertService;
import com.weather_api.WeatherApi.services.messageService.MessageService;
import com.weather_api.WeatherApi.services.messageService.MessageServiceImpl;
import com.weather_api.WeatherApi.services.userService.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
public class WeatherApiApplication {

	@Autowired
	private UserService userService;

	@Autowired
	private AlertService alertService;

	@Autowired
	private MessageService messageService;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public ObjectMapper getObjectMapper(){
		return new ObjectMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(WeatherApiApplication.class, args);

	}

}
