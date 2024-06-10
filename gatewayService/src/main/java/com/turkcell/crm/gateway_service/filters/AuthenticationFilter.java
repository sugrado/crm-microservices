package com.turkcell.crm.gateway_service.filters;

import com.turkcell.crm.gateway_service.clients.IdentityClient;
import com.turkcell.crm.gateway_service.rules.AuthenticationFilterRules;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final IdentityClient identityClient;
    private final AuthenticationFilterRules authenticationFilterRules;

    public AuthenticationFilter(IdentityClient identityClient, AuthenticationFilterRules authenticationFilterRules) {
        super(Config.class);
        this.identityClient = identityClient;
        this.authenticationFilterRules = authenticationFilterRules;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (!RouteValidator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }
            this.authenticationFilterRules.headersShouldContainAuthorizationHeader(exchange.getRequest().getHeaders());

            List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
            this.authenticationFilterRules.authHeadersCanNotBeEmptyOrNull(authHeaders);

            String authHeader = authHeaders.get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }

            this.authenticationFilterRules.authHeaderCanNotBeEmptyOrNull(authHeader);

            this.identityClient.validateToken(authHeader);

            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
