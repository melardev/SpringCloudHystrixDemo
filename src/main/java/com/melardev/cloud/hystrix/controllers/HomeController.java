package com.melardev.cloud.hystrix.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/recommendations")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getStudents() {
        String response = restTemplate.exchange("http://rest-app/recommendations/", HttpMethod.GET,
                null, new ParameterizedTypeReference<String>() {
                }).getBody();

        return response;
    }

    @GetMapping(value = "/safe")

    public String safe() {
        return "safe";
    }

    public String fallbackMethod() {

        return "{\"urls\": \"http://melardev.com/eng\"}";
    }


}
