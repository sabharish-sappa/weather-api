package com.weather_api.WeatherApi.models.CityWeather;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherDTO {

    private String temperature;
    private String humidity;
    private String min_temp;
    private String max_temp;
    private String pressure;
    private String sea_level;
    private String visibility;
    private String sunrise;
    private String sunset;


    public WeatherDTO(Weather weather){
        this.humidity = weather.main().humidity();
        this.min_temp = weather.main().temp_min();
        this.max_temp = weather.main().temp_max();
        this.pressure = weather.main().pressure();
        this.sea_level = weather.main().sea_level();
        this.visibility = weather.visibility();
        this.sunrise = weather.sys().sunrise();
        this.sunset = weather.sys().sunset();
        this.temperature = weather.main().temp();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSea_level() {
        return sea_level;
    }

    public void setSea_level(String sea_level) {
        this.sea_level = sea_level;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
