package com.cloud.eurekaclient.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class clientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/client")
    public String client() {
        //1、通过eurekaClient获取search服务的信息
        InstanceInfo search = eurekaClient.getNextServerFromEureka("search", false);
        //2、获取到访问的地址（地址+端口号）
        String homePageUrl = search.getHomePageUrl();
        //3、通过restTemplate访问
        String result = restTemplate.getForObject(homePageUrl + "/search", String.class);
        //4、返回
        return result;
    }
}
