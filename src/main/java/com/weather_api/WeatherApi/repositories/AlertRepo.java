package com.weather_api.WeatherApi.repositories;

import com.weather_api.WeatherApi.models.alerts.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepo extends JpaRepository<Alert,Long> {

}
