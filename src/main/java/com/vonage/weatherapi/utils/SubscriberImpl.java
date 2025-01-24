package com.vonage.weatherapi.utils;

import com.vonage.weatherapi.models.users.User;
import com.vonage.weatherapi.services.messageService.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriberImpl implements Subscriber {

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

