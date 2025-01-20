package com.weather_api.WeatherApi;

import com.weather_api.WeatherApi.models.alerts.AlertRequest;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.alertService.AlertService;
import com.weather_api.WeatherApi.services.userService.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoadingComponent {

    @Autowired
    AlertService alertService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() {
        System.out.println("Application has started!");
        loadDataIntoMemory();
    }


    public void loadDataIntoMemory(){
        userService.createUser(new User("Sabharish","8721234498","test1@gmail.com"));
        userService.createUser(new User("Siddhu","9876543234","test2@gmail.com"));
        userService.createUser(new User("Vinay","7738217362","test3@gmail.com"));
        userService.createUser(new User("Harshith","8798797667","test4@gmail.com"));
        userService.createUser(new User("Manish","8763612836","test5@gmail.com"));

        alertService.createAlert(new AlertRequest("storm","84.41","18.76",1L));
        alertService.createAlert(new AlertRequest("temp","17.04","74.43",1L));
        alertService.createAlert(new AlertRequest("storm","12.98","77.62",2L));
        alertService.createAlert(new AlertRequest("temp","12.98","77.62",3L));
        alertService.createAlert(new AlertRequest("storm","84.41","18.76",4L));
        alertService.createAlert(new AlertRequest("temp","12.98","77.62",5L));
    }
}
