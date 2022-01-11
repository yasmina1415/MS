package com.example.backendwrapper;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MainController {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    EurekaClient eurekaClient;

    @GetMapping("/wrappertest")
    public String getendpointdata(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("MYENDPOINT", false);
        String baseUrl = nextServerFromEureka.getHomePageUrl();//http://localhost:port
        return restTemplate.getForObject(baseUrl+"/test", String.class);

    }

    @GetMapping("/test")
    String Test (){
        return "from the wrapper";
    }
}
