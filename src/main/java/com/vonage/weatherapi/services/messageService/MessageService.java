package com.vonage.weatherapi.services.messageService;


public interface MessageService {
    void sendMessage(String mobileNumber, String message);
}
