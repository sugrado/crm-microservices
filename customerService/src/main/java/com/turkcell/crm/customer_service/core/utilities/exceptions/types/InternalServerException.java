package com.turkcell.crm.customer_service.core.utilities.exceptions.types;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
