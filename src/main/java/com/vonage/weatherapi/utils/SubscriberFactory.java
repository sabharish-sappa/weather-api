package com.vonage.weatherapi.utils;


import com.vonage.weatherapi.models.users.User;
import com.vonage.weatherapi.services.messageService.MessageService;
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
