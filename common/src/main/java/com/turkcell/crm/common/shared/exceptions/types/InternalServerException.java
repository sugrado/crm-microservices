package com.turkcell.crm.common.shared.exceptions.types;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
