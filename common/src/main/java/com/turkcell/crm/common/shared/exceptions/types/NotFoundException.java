package com.turkcell.crm.common.shared.exceptions.types;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
