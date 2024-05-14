package com.turkcell.crm.customer_service.core.utilities.exceptions.types;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
