package com.vonage.weatherapi.repositories;

import com.vonage.weatherapi.models.alerts.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepo extends JpaRepository<Alert,Long> {

}
