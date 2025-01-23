package com.weather_api.WeatherApi.controllers;

import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertRequest;
import com.weather_api.WeatherApi.services.alertService.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PatchMapping("/update/{id}")

    public Alert updateAlert(@PathVariable(name = "id") long alertId, @RequestBody AlertRequest updateAlertRequest)  {

        return alertService.updateAlert(updateAlertRequest,alertId);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteAlert(@PathVariable Long id){
        alertService.deleteAlert(id);
    }



}
