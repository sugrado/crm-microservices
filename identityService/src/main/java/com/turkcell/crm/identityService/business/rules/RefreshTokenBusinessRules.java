package com.turkcell.crm.identityService.business.rules;

import com.turkcell.crm.identityService.business.constants.Messages;
import com.turkcell.crm.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.identityService.entities.concretes.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RefreshTokenBusinessRules {
    public void refreshTokenShouldBeExist(Optional<RefreshToken> refreshToken) {
        if (refreshToken.isEmpty()) {
            throw new BusinessException(Messages.RefreshTokenMessages.REFRESH_TOKEN_NOT_FOUND);
        }
    }

    public void refreshTokenShouldNotBeExpired(RefreshToken refreshToken) {
        if (refreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException(Messages.RefreshTokenMessages.REFRESH_TOKEN_EXPIRED);
        }
    }

    public void refreshTokenShouldNotBeRevoked(RefreshToken refreshToken) {
        if (refreshToken.getRevokedDate() != null) {
            throw new BusinessException(Messages.RefreshTokenMessages.REFRESH_TOKEN_REVOKED);
        }
    }
}
