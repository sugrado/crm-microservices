package com.turkcell.crm.gateway_service.rules;

import com.turkcell.crm.gateway_service.constant.Messages;
import com.turkcell.crm.gateway_service.exceptions.types.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationFilterRules {

    public void headersShouldContainAuthorizationHeader(HttpHeaders headers) {
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new AuthenticationException(Messages.MISSING_AUTHORIZATION_HEADER);
        }
    }

    public void authHeadersCanNotBeEmptyOrNull(List<String> authHeaders) {
        if (authHeaders == null || authHeaders.isEmpty()) {
            throw new AuthenticationException(Messages.MISSING_AUTHORIZATION_HEADER);
        }
    }

    public void authHeaderCanNotBeEmptyOrNull(String authHeader) {
        if (authHeader == null || authHeader.isBlank() || authHeader.isEmpty()) {
            throw new AuthenticationException(Messages.MISSING_AUTHORIZATION_HEADER);
        }
    }
}
