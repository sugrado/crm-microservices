package com.turkcell.crm.gateway_service.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class IdentityClient {
    private final RestTemplate restTemplate;
    // TODO: localhost:7005 magic string to be replaced with a constant
    private static final String BASE_URL = "http://localhost:7005/identity-service/api/v1/";

    public IdentityClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        restTemplate.exchange(BASE_URL + "auth/validate-token", HttpMethod.GET, entity, Void.class);
    }
}
