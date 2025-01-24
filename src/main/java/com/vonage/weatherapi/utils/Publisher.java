package com.vonage.weatherapi.utils;

import com.vonage.weatherapi.models.alerts.Alert;

public interface Publisher {
    void subscribeToAlert(Alert alert);
    void unsubscribeToAlert(Long id);
    void checkForAlerts();
}

