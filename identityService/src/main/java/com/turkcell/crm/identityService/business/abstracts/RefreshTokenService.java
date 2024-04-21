package com.turkcell.crm.identityService.business.abstracts;

import com.turkcell.crm.identityService.entities.concretes.RefreshToken;
import com.turkcell.crm.identityService.entities.concretes.User;

public interface RefreshTokenService {
    RefreshToken create(User user);

    RefreshToken verify(String token);

    RefreshToken rotate(RefreshToken token, String ipAddress);

    void revokeOldTokens(User user, String ipAddress);
}
