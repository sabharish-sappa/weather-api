package com.weather_api.WeatherApi.services.messageService;


import org.springframework.stereotype.Service;


public interface MessageService {
    void sendMessage(String mobileNumber, String message);
}
