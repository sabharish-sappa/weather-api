package com.weather_api.WeatherApi.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseRawFormat {

    String humidity;
    String visibility;
    String pressure;
    @JsonProperty("temp_min")
    String minTemp;
    @JsonProperty("temp_max")
    String maxTemp;
    String sunrise;
    String sunset;
    String seaLevel;



    public WeatherResponseRawFormat(){

    }

    public WeatherResponseRawFormat(String humidity, String visibility, String pressure, String minTemp, String maxTemp, String sunrise, String sunset, String seaLevel) {
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.seaLevel =seaLevel;


    }


    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(String seaLevel) {
        this.seaLevel = seaLevel;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }



    @Override
    public String toString() {
        return "WeatherResponse{" +
                "humidity='" + humidity + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pressure='" + pressure + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", seaLevel='" + seaLevel + '\'' +
                '}';
    }
}
