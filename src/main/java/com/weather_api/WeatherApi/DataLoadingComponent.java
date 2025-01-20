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
        userService.createUser(new User("Sabharish","3242343243","test1@gmail.com"));
        userService.createUser(new User("Siddhu","76874664","test2@gmail.com"));
        userService.createUser(new User("Vinay","42343243223","test3@gmail.com"));
        userService.createUser(new User("Harshith","535435435","test4@gmail.com"));
        userService.createUser(new User("Manish","534543543","test5@gmail.com"));

        alertService.createAlert(new AlertRequest("storm","84.41","18.76",1L));
        alertService.createAlert(new AlertRequest("temp","84.41","18.76",1L));
        alertService.createAlert(new AlertRequest("storm","84.41","18.76",2L));
        alertService.createAlert(new AlertRequest("temp","84.41","18.76",3L));
        alertService.createAlert(new AlertRequest("storm","84.41","18.76",4L));
        alertService.createAlert(new AlertRequest("temp","84.41","18.76",5L));
    }
}
