package com.usersService.controller;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersServiceController {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("hello")
    private String hello(){
        return "hello";
    }

    @GetMapping("/users")
    public String getUsers(){
        String url = "http://localhost:8300/api/db-service/db/users";
        StringBuilder result = new StringBuilder();
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }catch (IOException e){
            System.out.println("error IO");
        }
        return result.toString();
    }
    @PostMapping("/users")
    public String addUser(@RequestBody String userData){
        String url = "http://localhost:8300/api/db-service/db/users";
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringBuilder result = new StringBuilder();
        try {

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            JSONObject data = new JSONObject(userData);
            String name = data.getString("user_name");
            String pass = data.getString("user_pass");
            urlParameters.add(new BasicNameValuePair(name,pass));
            System.out.println(urlParameters.toString());
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }catch (Exception e){
            System.out.println("error:" + e);
        }
        System.out.println(result.toString());
        return result.toString();
    }

}
