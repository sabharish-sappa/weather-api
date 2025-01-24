package com.vonage.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vonage.weatherapi.models.forecastWeather.ForecastWeather;


@JsonPropertyOrder({})
public class ForecastWeatherDTO {

    private String humidity;

    @JsonProperty("min_temp")
    private String minTemp;

    @JsonProperty("max_temp")
    private String maxTemp;
    private String pressure;

    @JsonProperty("sea_level")
    private String seaLevel;
    private String visibility;

    public ForecastWeatherDTO(ForecastWeather forecastWeather){
        this.humidity = Integer.toString(forecastWeather.main().humidity());
        this.minTemp = Double.toString(forecastWeather.main().minTemp());
        this.maxTemp = Double.toString(forecastWeather.main().maxTemp());
        this.pressure = Integer.toString(forecastWeather.main().pressure());
        this.seaLevel = Integer.toString(forecastWeather.main().seaLevel());
        this.visibility = Integer.toString(forecastWeather.visibility());
    }


    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
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

    public String getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(String seaLevel) {
        this.seaLevel = seaLevel;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}
