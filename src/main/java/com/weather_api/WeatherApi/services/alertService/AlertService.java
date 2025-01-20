package com.weather_api.WeatherApi.services.alertService;

import com.weather_api.WeatherApi.models.alerts.*;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.repositories.AlertRepo;
import com.weather_api.WeatherApi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    @Autowired
    private AlertRepo alertRepository;

    @Autowired
    private UserRepo userRepository;



    public Alert createAlert(AlertRequest alertRequest) {
        Optional<User> userOptional = userRepository.findById(alertRequest.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Alert alert = new Alert();
            alert.setAlertType(AlertType.valueOf(alertRequest.getAlertType().toUpperCase()));
            alert.setLat(alertRequest.getLat());
            alert.setLng(alertRequest.getLng());
            alert.setUser(user);

            user.addAlert(alert);
            System.out.println("Alert created successfully for user " + user.getName());

            return alertRepository.save(alert);
        } else {
            throw new RuntimeException("User with ID " + alertRequest.getUserId() + " not found.");
        }
    }

    public void deleteAlert(Long alertId) {
        Optional<Alert> alertOptional = alertRepository.findById(alertId);
        if (alertOptional.isPresent()) {
            Alert alert = alertOptional.get();
            alert.getUser().removeAlert(alert);
            alertRepository.delete(alert);
            System.out.println("Alert deleted successfully.");
        } else {
            throw new RuntimeException("Alert with ID " + alertId + " not found.");
        }
    }

    public List<Alert> getAllAlerts(){
        return alertRepository.findAll();
    }

    public Optional<Alert> getAlertById(Long id){
        return alertRepository.findById(id);
    }

    public Alert updateAlert(AlertRequest alertRequest){
        Optional<User> userOptional = userRepository.findById(alertRequest.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Alert alert = new Alert();
            alert.setAlertType(AlertType.valueOf(alertRequest.getAlertType().toUpperCase()));
            alert.setLat(alertRequest.getLat());
            alert.setLng(alertRequest.getLng());
            alert.setUser(user);

            user.addAlert(alert);
            System.out.println("Alert created successfully for user " + user.getName());

            return alertRepository.save(alert);
        } else {
            throw new RuntimeException("User with ID " + alertRequest.getUserId() + " not found.");
        }
    }


}