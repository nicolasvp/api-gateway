package com.phrases.apigateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Implement a Pre Filter for Zuul
 */
@Slf4j
@Component
public class PreTimeElapsedFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    // Calcula el tiempo de inicio del request
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request routed to %s", request.getMethod(), request.getRequestURL().toString()));

        Long initTime = System.currentTimeMillis();
        request.setAttribute("initTime", initTime);

        return null;
    }
}
