package com.cloud.search.controller;

import com.cloud.search.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    @Value("${server.port}")
    private String port;

    @Value("${version}")
    private String version;

    @GetMapping("/version")
    public String getVersion() {
        return version;
    }

    @GetMapping("/search")
    public String search() {
        int i = 1/0;
        return "search: " + port;
    }

    @GetMapping("/findById/{id}")
    public Customer findById(@PathVariable Integer id) {
        Customer entity = new Customer();
        entity.setId(1).setName("张三").setAge(20);
        return entity;
    }

    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam Integer id, @RequestParam String name) {
        Customer entity = new Customer();
        entity.setId(id).setName(name).setAge(20);
        return entity;
    }

    @PostMapping("/save")
    public Customer save(@RequestBody Customer entity) {
        return entity;
    }
}
