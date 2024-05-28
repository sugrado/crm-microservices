package com.turkcell.crm.gateway_service.exceptions.types;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
