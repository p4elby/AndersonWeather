package com.dbService.controller;

import com.dbService.entity.Forecast;
import com.dbService.repositories.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/db")
public class DbServiceController {

    @Autowired
    private ForecastRepository forecastRepository;

    @GetMapping("/forecast")
    public @ResponseBody
    Iterable<Forecast> getAllForecasts(){
        return forecastRepository.findAll();
    }

    @PostMapping("/forecast/db")
    public Forecast newForecast (@RequestBody Forecast newForecast){
        return forecastRepository.save(newForecast);
    }

    @PostMapping("/forecast/site")
    public Forecast newForecasts(@RequestBody String data){
        System.out.println(data);
        String[] splitData = data.split("&");
        System.out.println(splitData[7]);
        splitData[7] = splitData[7].replace("%2F","/");
        String city = splitData[0].split("=")[1];
        String visibility = splitData[1].split("=")[1];
        String wind = splitData[2].split("=")[1];
        int temperature = Integer.parseInt(splitData[3].split("=")[1]);
        int humidity = Integer.parseInt(splitData[4].split("=")[1]);
        int pressure = Integer.parseInt(splitData[5].split("=")[1]);
        int precipitation = Integer.parseInt(splitData[6].split("=")[1]);
        Date date = new Date();
        try {
            date =  new SimpleDateFormat("yyyy-MM-dd").parse(splitData[7].split("=")[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return forecastRepository.save(new Forecast(city, visibility, wind, temperature, humidity, pressure, precipitation, date));
    }
}

