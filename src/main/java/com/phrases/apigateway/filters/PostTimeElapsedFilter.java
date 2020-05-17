package com.phrases.apigateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Implement a Post Filter for Zuul
 */
@Slf4j
@Component
public class PostTimeElapsedFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {


        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("Entering to post filter");

        Long initTime = (Long) request.getAttribute("initTime");
        Long finalTime = System.currentTimeMillis();
        Long timeElapsed = finalTime - initTime;

        log.info(String.format("Time elapsed in seconds %s.", timeElapsed.doubleValue() / 1000.00));
        log.info(String.format("Time elapsed in milliseconds %s ms.", timeElapsed));
        return null;
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

}
