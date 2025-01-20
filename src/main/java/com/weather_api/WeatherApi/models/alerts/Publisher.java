package com.weather_api.WeatherApi.models.alerts;

public interface Publisher {
    void subscribeToAlert(Alert alert);
    void unsubscribeToAlert(Long id);

    void notifySubscriber();
}

