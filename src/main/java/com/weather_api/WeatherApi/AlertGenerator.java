package com.weather_api.WeatherApi;

import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertType;
import com.weather_api.WeatherApi.models.alerts.Location;
import com.weather_api.WeatherApi.models.cityWeather.CityWeatherDTO;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.userService.UserService;
import com.weather_api.WeatherApi.services.weatherService.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AlertGenerator implements Publisher {

    @Autowired
    WeatherService weatherService;

    @Autowired
    UserService userService;

    List<Alert> alerts = new ArrayList<>();


    @Override
    public void subscribeToAlert(Alert alert) {
        alerts.add(alert);
        System.out.println("Successfully Subscribed for the "+alert.getAlertType()+" alerts");
    }

    @Override
    public void unsubscribeToAlert(Long id) {

        for(var alert: alerts){
            if(alert.getId()==id) {
                {
                    alerts.remove(alert);
                    System.out.println("Successfully unsubscribed for the "+alert.getAlertType()+" alerts");
                    return;
                }
            }
        }

//        handle exception, that is not there some thing like that

    }

    @Override
    public void notifySubscriber() {

        for(var alert:alerts){
        }
    }


//    needs to check for every 10 mins

    @Scheduled(cron = "*/30 * * * * *") // Every 10 minutes
    @Async
    @Override
    public void checkForAlerts(){

        System.out.println("check for alerts executed");
        for(var alert:alerts){
//            get the weather
            Location location = new Location(alert.getLat(),alert.getLng());
            CityWeatherDTO locationWeather = weatherService.getParticularLocationWeather(location);

//            check if any disturbance in the values so that to generate an alert

            Boolean isAlertNeedToBeTriggered = isTriggerRequired(locationWeather,alert.getAlertType());

            if(isAlertNeedToBeTriggered)
            {
                User user = alert.getUser();
                user.triggerAlert(alert.getAlertType());
            }
        }
    }

    Boolean isTriggerRequired(CityWeatherDTO locationWeather, AlertType alertType){
        if(alertType.equals(AlertType.STORM))
        {
            try {
                double humidityValue = Double.parseDouble(locationWeather.getHumidity());
                double pressureValue = Double.parseDouble(locationWeather.getPressure());
                double visibilityValue = Double.parseDouble(locationWeather.getVisibility());

                if (humidityValue > 85 && pressureValue < 1000 && visibilityValue < 2) {
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for weather parameters.");
            }
            return false;

        }
        else if(alertType.equals(AlertType.TEMP)){
            try {
                double temp = Double.parseDouble(locationWeather.getTemperature());
                double min = Double.parseDouble(locationWeather.getMin_temp());
                double max = Double.parseDouble(locationWeather.getMax_temp());

                if (temp > 30 || max > 40) {
                    return true;
                }

                if (temp < 0 || min < 0) {
                    return true;
                }


            } catch (NumberFormatException e) {
                System.out.println("Invalid input for temperature parameters.");
            }

            return false;
        }

       return false;
    }
}
