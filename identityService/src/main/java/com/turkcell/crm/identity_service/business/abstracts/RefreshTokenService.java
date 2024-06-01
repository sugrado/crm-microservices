package com.turkcell.crm.identity_service.business.abstracts;

import com.turkcell.crm.identity_service.entities.concretes.RefreshToken;
import com.turkcell.crm.identity_service.entities.concretes.User;

public interface RefreshTokenService {
    RefreshToken create(User user);

    RefreshToken verify(String token);

    RefreshToken rotate(RefreshToken token, String ipAddress);

    void revokeToken(RefreshToken token, String ipAddress, String reason);

    void revokeOldTokens(User user, String ipAddress);
}
