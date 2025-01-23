package com.weather_api.WeatherApi;

import com.weather_api.WeatherApi.models.users.User;

public interface Subscriber {

    void triggerAlertMessageOverSms(String message);
}
