package com.example.endpointservice;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;

@RestController
public class MainController {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    EurekaClient eurekaClient;
    @GetMapping("/test")
    String Test (){
        return "from the endpoint";
    }

    @GetMapping("/endpointtest")
    public String getendpointdata(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("my-wrapper", false);
        String baseUrl = nextServerFromEureka.getHomePageUrl();//http://localhost:port
        return restTemplate.getForObject(baseUrl+"/test", String.class);

    }
}
