package com.turkcell.crm.gateway_service.filters;

import com.turkcell.crm.gateway_service.clients.IdentityClient;
import com.turkcell.crm.gateway_service.constant.Messages;
import com.turkcell.crm.gateway_service.exceptions.types.AuthenticationException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final IdentityClient identityClient;

    public AuthenticationFilter(IdentityClient identityClient) {
        super(Config.class);
        this.identityClient = identityClient;
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

            this.identityClient.validateToken(authHeader);

            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
