package com.weather_api.WeatherApi;


import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.messageService.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriberFactory {

    @Autowired
    MessageService messageService;

    public Subscriber createSubscriber(User user){
        return new SubscriberImpl(user, messageService);
    }
}
