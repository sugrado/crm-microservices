package com.turkcell.crm.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        String jwtHeader = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            return;
        }
        String jwt = jwtHeader.substring(7);
        requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", jwt));
    }
}
