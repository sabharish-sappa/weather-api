package com.vonage.weatherapi.models.cityWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CityWeatherSys(String sunrise, String sunset){

    public String toFormatted(String timeStamp) {

        Timestamp ts = new Timestamp(Long.parseLong(timeStamp)*1000);
        Date date = new Date();
        date.setTime(ts.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a z");
        return formatter.format(date);

    }

    public CityWeatherSys(String sunrise, String sunset){
        this.sunrise = toFormatted(sunrise);
        this.sunset = toFormatted(sunset);
    }
}