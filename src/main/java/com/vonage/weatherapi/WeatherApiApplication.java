package com.vonage.weatherapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonage.weatherapi.services.alertService.AlertService;
import com.vonage.weatherapi.services.messageService.MessageService;
import com.vonage.weatherapi.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
