package com.cloud.eurekaclient.factory;

import com.cloud.eurekaclient.ServerClient.SearchServer;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchServerFallBackFactory implements FallbackFactory<SearchServer> {
    @Override
    public SearchServer create(Throwable throwable) {
        throwable.printStackTrace();
        return null;
    }
}
