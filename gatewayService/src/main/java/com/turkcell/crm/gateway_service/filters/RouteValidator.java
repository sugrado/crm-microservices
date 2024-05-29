package com.turkcell.crm.gateway_service.filters;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.function.Predicate;

public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "identity-service/api/v1/auth/register",
            "identity-service/api/v1/auth/login",
            "identity-service/api/v1/auth/refresh"
    );

    public static final Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
