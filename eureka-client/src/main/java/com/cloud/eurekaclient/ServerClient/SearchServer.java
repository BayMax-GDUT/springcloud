package com.cloud.eurekaclient.ServerClient;

import com.cloud.eurekaclient.entity.Customer;
import com.cloud.eurekaclient.factory.SearchServerFallBackFactory;
import com.cloud.eurekaclient.fallback.SearchServerFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

@FeignClient(value = "SEARCH", fallbackFactory = SearchServerFallBackFactory.class) // 指定服务名称
public interface SearchServer {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    String search();

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    Customer findById (@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    Customer getCustomer(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name);

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    Customer save(@RequestBody Customer entity);
}
