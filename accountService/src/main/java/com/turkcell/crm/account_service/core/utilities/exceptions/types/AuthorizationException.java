package com.turkcell.crm.account_service.core.utilities.exceptions.types;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
