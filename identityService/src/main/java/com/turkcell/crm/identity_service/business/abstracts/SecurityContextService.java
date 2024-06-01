package com.turkcell.crm.identity_service.business.abstracts;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface SecurityContextService {
    String getUsername();

    List<String> getRoles();

    Authentication getAuthentication();
}
