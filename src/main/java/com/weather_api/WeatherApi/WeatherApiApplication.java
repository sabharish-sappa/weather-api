package com.weather_api.WeatherApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertRequest;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.alertService.AlertService;
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
@EnableAsync
@EnableScheduling
public class WeatherApiApplication {

	@Autowired
	private UserService userService;

	@Autowired
	private AlertService alertService;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public ObjectMapper getObjectMapper(){
		return new ObjectMapper();
	}

//	@PostConstruct
//	public void loadDataIntoMemory(){
//		userService.createUser(new User("Sabharish","3242343243","test1@gmail.com"));
//		userService.createUser(new User("Siddhu","76874664","test2@gmail.com"));
//		userService.createUser(new User("Vinay","42343243223","test3@gmail.com"));
//		userService.createUser(new User("Harshith","535435435","test4@gmail.com"));
//		userService.createUser(new User("Manish","534543543","test5@gmail.com"));
//
//		alertService.createAlert(new AlertRequest("storm","84.41","18.76",1L));
//		alertService.createAlert(new AlertRequest("temp","84.41","18.76",1L));
//		alertService.createAlert(new AlertRequest("storm","84.41","18.76",2L));
//		alertService.createAlert(new AlertRequest("temp","84.41","18.76",3L));
//		alertService.createAlert(new AlertRequest("storm","84.41","18.76",4L));
//		alertService.createAlert(new AlertRequest("temp","84.41","18.76",5L));
//	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherApiApplication.class, args);
	}

}
