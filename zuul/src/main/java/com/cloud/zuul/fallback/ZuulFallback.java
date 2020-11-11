package com.cloud.zuul.fallback;

import com.netflix.ribbon.http.HttpRequestTemplate;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ZuulFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*"; // 代表所有出现问题的服务，都走这个降级方法
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        System.out.printf("降级的服务为%s", route);
        cause.printStackTrace();
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // 返回HttpStatus的相关信息
                return HttpStatus.NOT_FOUND;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 返回HttpStatus的相关信息
                return HttpStatus.NOT_FOUND.value();
            }

            @Override
            public String getStatusText() throws IOException {
                // 返回HttpStatus的相关信息
                return HttpStatus.NOT_FOUND.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                // 返回的数据
                String msg = "当前服务" + route + "出现问题";
                return new ByteArrayInputStream(msg.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
