package com.weather_api.WeatherApi;

import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertType;
import com.weather_api.WeatherApi.models.alerts.Location;
import com.weather_api.WeatherApi.models.cityWeather.CityWeatherDTO;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.alertService.AlertService;
import com.weather_api.WeatherApi.services.userService.UserService;
import com.weather_api.WeatherApi.services.weatherService.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;


@Component
public class AlertGenerator implements Publisher {

    @Autowired
    WeatherService weatherService;

    @Autowired
    AlertService alertService;


    @Autowired
    SubscriberFactory subscriberFactory;

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



    @Scheduled(cron = "*/30 * * * * *") // Every 10 minutes
    @Async
    @Override
    public void checkForAlerts(){

        alerts = alertService.getAllAlerts();

        System.out.println();
        System.out.println("check for alerts executed");
        for(var alert:alerts){
            Location location = new Location(alert.getLat(),alert.getLng());
            CityWeatherDTO locationWeather = weatherService.getParticularLocationWeather(location);
            Integer isAlertRequired = isTriggerRequired(locationWeather,alert.getAlertType());

            if(isAlertRequired!=0)
            {
                User user = alert.getUser();
                String alertMessageToBeSent = getAlertMessage(alert.getAlertType(),isAlertRequired);
                Subscriber subscriber = subscriberFactory.createSubscriber(user);
                subscriber.triggerAlertMessageOverSms(alertMessageToBeSent);

            }
        }
    }

    int isTriggerRequired(CityWeatherDTO locationWeather, AlertType alertType){
        if(alertType.equals(AlertType.STORM))
        {
            try {
                double humidityValue = Double.parseDouble(locationWeather.getHumidity());
                double pressureValue = Double.parseDouble(locationWeather.getPressure());
                double visibilityValue = Double.parseDouble(locationWeather.getVisibility());

                if (humidityValue > 85 && pressureValue < 1000 && visibilityValue < 2) {
                    return 1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for weather parameters.");
            }
            return 0;

        }
        else if(alertType.equals(AlertType.TEMP)){
            try {
                double temp = Double.parseDouble(locationWeather.getTemperature());
                double min = Double.parseDouble(locationWeather.getMin_temp());
                double max = Double.parseDouble(locationWeather.getMax_temp());

                if (temp > 25 || max > 40)
                    return 1;

                if (temp < 0 || min < 0)
                    return -1;


            } catch (NumberFormatException e) {
                System.out.println("Invalid input for temperature parameters.");
            }

            return 0;
        }

       return 0;
    }

    String getAlertMessage(AlertType alertType, int condition)
    {

        String result="";
        if(condition<0)
        {
            return "Lower "+alertType.toString()+" values are predicted in your area";
        }

        else if(condition>0)
        {
            return "Higher "+alertType.toString()+" values are predicted recorded in your area";
        }
        else {
            return ""+alertType.toString()+" alert is predicted in your area.";
        }

    }
}
