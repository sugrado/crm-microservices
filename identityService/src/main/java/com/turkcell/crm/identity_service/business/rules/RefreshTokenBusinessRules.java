package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.core.business.abstracts.MessageService;
import com.turkcell.crm.identity_service.entities.concretes.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RefreshTokenBusinessRules {
    private final MessageService messageService;

    public void refreshTokenShouldBeExist(Optional<RefreshToken> refreshToken) {
        if (refreshToken.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.RefreshTokenMessages.REFRESH_TOKEN_NOT_FOUND));
        }
    }

    public void refreshTokenShouldNotBeExpired(RefreshToken refreshToken) {
        if (refreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException(messageService.getMessage(Messages.RefreshTokenMessages.REFRESH_TOKEN_EXPIRED));
        }
    }

    public void refreshTokenShouldNotBeRevoked(RefreshToken refreshToken) {
        if (refreshToken.getRevokedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.RefreshTokenMessages.REFRESH_TOKEN_REVOKED));
        }
    }
}
