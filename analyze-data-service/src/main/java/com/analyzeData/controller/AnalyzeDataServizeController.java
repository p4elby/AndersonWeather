package com.analyzeData.controller;


import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/analyze-service")
@RestController
public class AnalyzeDataServizeController {
    @PostMapping("/analyze")
    public  String analyze(@RequestBody String data){
        String result="";
        try {
            JSONObject forecast = new JSONObject(data);
            int temperature = Integer.parseInt(forecast.getString("temperature"));
            int precipitation = Integer.parseInt(forecast.getString("precipitation"));
            String wind = forecast.getString("wind");
            if (temperature < -5){
                result += "Winter clothes";
            } else if (temperature < 10) {
                result += "Windbreaker with a sweatshirt";
            } else if(temperature < 20){
                result += "Windbreaker";
            }else
                result += "Shorts with a t-shirt";

            if (precipitation < 20 && temperature > 0){
                result += ",the probability of rain is small! so you should can not take an umbrella";
            } else
                if (precipitation <50 && temperature > 0){
                    result += ",the probability of rain is 50%! so rely on your luck and decide whether or not to take an umbrella";
                } else
                    if ( temperature > 0) {
                        result += ",take an umbrella with you";
                    }else
                        result += ",the likelihood of snowing is high";


            if ("Calm".equals(wind) || "Quiet".equals(wind)) {
                result += ",will be windless";
            } else if ("Light".equals(wind) || "Weak".equals(wind) || "Fresh".equals(wind) || "Moderate".equals(wind)) {
                result += ",fresh wind will blow";
            } else if ("Strong".equals(wind)) {
                result += ",strong wind will blow";
            } else if ("Storm".equals(wind)) {
                result += ",maybe worth staying at home";
            } else if ("Hurricane".equals(wind)) {
                result += ",you should stay home";
            } else
                result += ",wind strength will vary throughout the day";
        }catch (Exception exp){
            System.out.println(exp);
        }
        return result;
    }

}
