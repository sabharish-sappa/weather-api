package com.weather_api.WeatherApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Weather (Main main, String visibility,Sys sys ){}

@JsonIgnoreProperties(ignoreUnknown = true)
record Main(String temp, String temp_min, String temp_max, String pressure, String humidity, String sea_level){}

@JsonIgnoreProperties(ignoreUnknown = true)
record Sys(String sunrise, String sunset){

    public String toFormatted(String timeStamp) {

        Timestamp ts = new Timestamp(Long.parseLong(timeStamp)*1000);
        Date date = new Date();
        date.setTime(ts.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a z");
        return formatter.format(date);

    }

    Sys(String sunrise, String sunset){
        this.sunrise = toFormatted(sunrise);
        this.sunset = toFormatted(sunset);
    }
}
