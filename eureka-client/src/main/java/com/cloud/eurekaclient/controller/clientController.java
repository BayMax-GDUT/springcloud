package com.cloud.eurekaclient.controller;

import com.cloud.eurekaclient.ServerClient.SearchServer;
import com.cloud.eurekaclient.entity.Customer;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class clientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SearchServer searchServer;

    @GetMapping("/search")
    public String search() {
        // 通过restTemplate访问
//        String result = restTemplate.getForObject("http://SEARCH/search", String.class);

        String result = searchServer.search();
        // 返回
        return result;
    }

    @GetMapping("/findById/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallBack")
    public Customer findById(@PathVariable(value = "id") Integer id) {
        int i = 1 / 0;
        return searchServer.findById(id);
    }

    public Customer findByIdFallBack(@PathVariable(value = "id") Integer id) {
        Customer customer = new Customer();
        customer.setId(-1).setAge(23).setName("av");
        return customer;
    }

    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name) {
        return searchServer.getCustomer(id, name);
    }

    @GetMapping("/save")
    public Customer save(Customer entity) {
        return searchServer.save(entity);
    }
}
