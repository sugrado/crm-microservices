package com.turkcell.crm.gateway_service.configurations;

import com.turkcell.crm.gateway_service.exceptions.handlers.RestTemplateErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    private final RestTemplateErrorHandler restTemplateErrorHandler;

    public RestTemplateConfiguration(RestTemplateErrorHandler restTemplateErrorHandler) {
        this.restTemplateErrorHandler = restTemplateErrorHandler;
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder().errorHandler(restTemplateErrorHandler).build();
    }
}
