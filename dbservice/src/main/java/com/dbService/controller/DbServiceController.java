package com.dbService.controller;

import com.dbService.entity.Forecast;
import com.dbService.entity.User;
import com.dbService.repositories.ForecastRepository;
import com.dbService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db")
public class DbServiceController {

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/forecasts")
    public @ResponseBody
    Iterable<Forecast> getAllForecasts(){
        return forecastRepository.findAll();
    }

    @PostMapping("/forecast")
    public Forecast newForecast (@RequestBody Forecast newForecast){
        return forecastRepository.save(newForecast);
    }

    @GetMapping("/users")
    private  @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    private User newUser (@RequestBody String data){
        System.out.println(data);
        String[] dataUser = data.split("=");
        String name = dataUser[0];
        String pass = dataUser[1];
        User newUser = new User(name,pass);
        System.out.println(newUser.toString());
        return userRepository.save(newUser);
    }

}

