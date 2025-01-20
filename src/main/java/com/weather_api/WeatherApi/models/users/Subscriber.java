package com.weather_api.WeatherApi.models.users;

import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertType;

public interface Subscriber {

    void triggerAlert(AlertType alertType);
}
