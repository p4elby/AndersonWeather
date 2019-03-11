package com.dbService.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_forecast")
    private int idForecast;
    private String forecast_city;
    private String forecast_visibility;
    private String forecast_wind;
    private int forecast_temperature;
    private int forecast_humidity;
    private int forecast_pressure;
    private int forecast_precipitation;
    private Date forecast_date;

    public Forecast() {
    }

    public int getId() {
        return idForecast;
    }

    public void setId(int id) {
        this.idForecast = id;
    }

    public String getVisibility() {
        return forecast_visibility;
    }

    public void setVisibility(String visibility) {
        this.forecast_visibility = visibility;
    }

    public String getWind() {
        return forecast_wind;
    }

    public void setWind(String wind) {
        this.forecast_wind = wind;
    }

    public int getTemp() {
        return forecast_temperature;
    }

    public void setTemp(int temp) {
        this.forecast_temperature = temp;
    }

    public int getHumidity() {
        return forecast_humidity;
    }

    public void setHumidity(int humidity) {
        this.forecast_humidity = humidity;
    }

    public int getPresuure() {
        return forecast_pressure;
    }

    public void setPresuure(int presuure) {
        this.forecast_pressure = presuure;
    }

    public int getPrecipitation() {
        return forecast_precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.forecast_precipitation = precipitation;
    }

    public String getForecast_city() {
        return forecast_city;
    }

    public void setForecast_city(String forecast_city) {
        this.forecast_city = forecast_city;
    }

    public Date getDate() {
        return forecast_date;
    }

    public void setDate(Date date) {
        this.forecast_date = date;
    }

    @Override
    public String toString() {
        return  "  city='"+forecast_city+
                ", visibility='" + forecast_visibility +
                ", wind='" + forecast_wind +
                ", temp=" + forecast_temperature +
                ", humidity=" + forecast_humidity +
                ", presuure=" + forecast_pressure +
                ", precipitation=" + forecast_precipitation +
                ", date=" + forecast_date;
    }
}
