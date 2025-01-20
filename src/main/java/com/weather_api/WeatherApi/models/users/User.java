package com.weather_api.WeatherApi.models.users;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.weather_api.WeatherApi.models.alerts.Alert;
import com.weather_api.WeatherApi.models.alerts.AlertType;
import com.weather_api.WeatherApi.models.alerts.Location;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Subscriber {
    @Id
    @GeneratedValue()
    Long id;
    String name;
    String mobileNumber;
    String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Alert> alerts = new ArrayList<>();

    public User(){};


    public User(String name, String mobileNumber, String email) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    public void removeAlert(Alert alert) {
        alerts.remove(alert);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    @Override
    public void triggerAlert(AlertType alertType) {

        if(alertType.equals(AlertType.STORM)){
            System.out.println("sent Storm Alert for the user "+ name);
            System.out.println("Alert Message - Severe Thunderstorm Warning! sent successfully to user "+name+ " number - "+mobileNumber);
        }

        else if(alertType.equals(AlertType.TEMP)){
            System.out.println("sent Temp Alert for the user "+name);
            System.out.println("Alert Message - Severe Temperature Warning! sent successfully to user "+name+ " number - "+mobileNumber);
        }

        else{
            System.out.println("sent Alert for the user "+name);
            System.out.println("Alert Message sent successfully to user "+name+ " number - "+mobileNumber);
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
