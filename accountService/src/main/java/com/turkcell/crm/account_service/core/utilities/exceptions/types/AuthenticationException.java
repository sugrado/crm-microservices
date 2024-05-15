package com.turkcell.crm.account_service.core.utilities.exceptions.types;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
