package com.turkcell.crm.account_service.core.utilities.exceptions.types;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {
    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    private Map<String, String> errors;
}
