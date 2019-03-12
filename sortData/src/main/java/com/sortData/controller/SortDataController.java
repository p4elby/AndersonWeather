package com.sortData.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sort")
public class SortDataController {
    @GetMapping("/sort")
    public String sortForecast(@RequestBody String sortType){
        
        String result = "";
        return result;
    }
}
