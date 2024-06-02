package com.turkcell.crm.identity_service.core.business.abstracts;

public interface MessageService {
    String getMessage(String key);

    String getMessage(String key, Object[] args);
}
