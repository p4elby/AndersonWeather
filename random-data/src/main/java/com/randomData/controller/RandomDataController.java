package com.randomData.controller;

import com.randomData.entity.Forecast;
import com.randomData.enums.Visibility;
import com.randomData.enums.Wind;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/random")
public class RandomDataController {
    @PostMapping("/generate")
    public Forecast randomForecast(@RequestBody String city){
        Visibility[] allVis = Visibility.values();
        Wind[] allWind = Wind.values();
        Random random = new Random();
        int pickWind = random.nextInt(allWind.length);
        int pickVis =  random.nextInt(allVis.length);
        String visibility = allVis[pickVis].getVis();
        String wind = allWind[pickWind].getWind();
        System.out.println("Vis: "+ visibility);
        System.out.println("Wind: "+ wind);
        int temperature = random.nextInt(30) - random.nextInt(30);
        int humidity = random.nextInt(100);
        int pressure = random.nextInt(1000) ;
        int precipitation =random.nextInt(100);
        Date date = new Date();
        Forecast forecast = new Forecast(city, visibility, wind, temperature, humidity, pressure, precipitation, date);
        return forecast;
    }

}
