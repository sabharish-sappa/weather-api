package com.weather_api.WeatherApi;

import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.messageService.MessageService;
import com.weather_api.WeatherApi.services.messageService.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SubscriberImpl implements Subscriber{

    @Autowired
    MessageService messageService;

    User user;

    public SubscriberImpl(User user) {
        this.user = user;
    }

    public SubscriberImpl(User user, MessageService messageService) {
        this.user = user;
        this.messageService = messageService;
    }

    @Override
    public void triggerAlertMessageOverSms(String message) {

        messageService.sendMessage(user.getMobileNumber(), message);

        }


    }

