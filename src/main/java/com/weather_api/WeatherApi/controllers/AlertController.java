package com.weather_api.WeatherApi.controllers;

import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertRequest;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.alertService.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/alert")
public class AlertController {

    @Autowired
    AlertService alertService;

    @GetMapping("")
    public List<Alert> getAllAlerts(){
        return alertService.getAllAlerts();
    }

    @GetMapping("/{id}")
    public Optional<Alert> getAlertById(@PathVariable Long id) {
        return alertService.getAlertById(id);
    }

    @PostMapping("/register")
    public Alert createAlert(@RequestBody AlertRequest alertRequest) {
        System.out.println(alertRequest.toString());
        return alertService.createAlert(alertRequest);
    }


//    some issue is there, fix it
    @PutMapping("/update")
    public Alert updateAlert(@RequestBody AlertRequest updateAlertRequest)  {

        Optional<Alert>alert  = alertService.getAlertById(updateAlertRequest.getId());

        if(alert.isPresent())
        return alertService.updateAlert(updateAlertRequest);

        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAlert(@PathVariable Long id){
        alertService.deleteAlert(id);
    }



}
