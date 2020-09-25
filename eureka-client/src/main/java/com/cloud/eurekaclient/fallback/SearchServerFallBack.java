package com.cloud.eurekaclient.fallback;

import com.cloud.eurekaclient.ServerClient.SearchServer;
import com.cloud.eurekaclient.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class SearchServerFallBack implements SearchServer {

    @Override
    public String search() {
        return "出现问题";
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Customer getCustomer(Integer id, String name) {
        return null;
    }

    @Override
    public Customer save(Customer entity) {
        return null;
    }
}
