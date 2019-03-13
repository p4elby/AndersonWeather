package com.randomData.controller;

import com.randomData.entity.Forecast;
import com.randomData.enums.Visibility;
import com.randomData.enums.Wind;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.http.client.methods.HttpPost;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/random")
public class RandomDataController {
    @PostMapping("/generate")
    public String randomForecast(@RequestBody String nameCity){
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
        DateFormat df = new SimpleDateFormat("yyyy/dd/MM");
        String dateStr = df.format(date);
        String url = "http://localhost:8300/api/db-service/db/forecast/site";
        StringBuilder result = new StringBuilder();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        try {
            JSONObject cityJson = new JSONObject(nameCity);
            String city =cityJson.getString("city");
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("city",city));
            urlParameters.add(new BasicNameValuePair("visibility",visibility));
            urlParameters.add(new BasicNameValuePair("wind",wind));
            urlParameters.add(new BasicNameValuePair("temperature",Integer.toString(temperature)));
            urlParameters.add(new BasicNameValuePair("humidity",Integer.toString(humidity)));
            urlParameters.add(new BasicNameValuePair("pressure",Integer.toString(pressure)));
            urlParameters.add(new BasicNameValuePair("precipitation",Integer.toString(precipitation)));
            urlParameters.add(new BasicNameValuePair("date",dateStr));
            System.out.println(urlParameters);
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return result.toString();
    }

}
