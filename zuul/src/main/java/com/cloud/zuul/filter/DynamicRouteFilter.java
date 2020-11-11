package com.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DynamicRouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取Request对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 获取参数 redisKey(模拟redis数据)
        String redisKey = request.getParameter("redisKey");
        // 判断
        if (redisKey != null && redisKey.equalsIgnoreCase("search")) {
            context.put(FilterConstants.SERVICE_ID_KEY, "search-v1");
            context.put(FilterConstants.REQUEST_URI_KEY, "/version");
        } else if (redisKey != null && redisKey.equalsIgnoreCase("client")) {
            context.put(FilterConstants.SERVICE_ID_KEY, "client");
            context.put(FilterConstants.REQUEST_URI_KEY, "/findById/1");
        }
        return null;
    }
}
