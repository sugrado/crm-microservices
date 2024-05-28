package com.turkcell.crm.gateway_service.filters;

import com.turkcell.crm.gateway_service.constant.Messages;
import com.turkcell.crm.gateway_service.exceptions.types.AuthenticationException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final RestTemplate restTemplate;

    public AuthenticationFilter(RestTemplate restTemplate) {
        super(Config.class);
        this.restTemplate = restTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (!RouteValidator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new AuthenticationException(Messages.MISSING_AUTHORIZATION_HEADER);
            }

            List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
            if (authHeaders == null || authHeaders.isEmpty()) {
                throw new AuthenticationException(Messages.MISSING_AUTHORIZATION_HEADER);
            }

            String authHeader = authHeaders.get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }

            if (authHeader == null || authHeader.isBlank() || authHeader.isEmpty()) {
                throw new AuthenticationException(Messages.MISSING_AUTHORIZATION_HEADER);
            }

            sendValidateTokenRequest(authHeader);

            return chain.filter(exchange);
        });
    }

    private void sendValidateTokenRequest(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        // TODO: URL magic string to be replaced with a constant
        restTemplate.exchange("http://localhost:7005/identity-service/api/v1/auth/validate-token", HttpMethod.GET, entity, Void.class);
    }

    public static class Config {
    }
}
