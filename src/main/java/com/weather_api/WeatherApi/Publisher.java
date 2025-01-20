package com.weather_api.WeatherApi;

import com.weather_api.WeatherApi.models.alerts.Alert;

public interface Publisher {
    void subscribeToAlert(Alert alert);
    void unsubscribeToAlert(Long id);

    void checkForAlerts();
}

