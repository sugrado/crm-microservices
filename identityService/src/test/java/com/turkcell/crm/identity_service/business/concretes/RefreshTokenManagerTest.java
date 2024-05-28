package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.core.services.JwtService;
import com.turkcell.crm.identity_service.business.abstracts.RefreshTokenService;
import com.turkcell.crm.identity_service.business.rules.RefreshTokenBusinessRules;
import com.turkcell.crm.identity_service.data_access.abstracts.RefreshTokenRepository;
import com.turkcell.crm.identity_service.entities.concretes.RefreshToken;
import com.turkcell.crm.identity_service.entities.concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RefreshTokenManagerTest {
    private RefreshTokenRepository refreshTokenRepository;
    private RefreshTokenService refreshTokenService;

    @BeforeEach
    void setUp() {
        refreshTokenRepository = Mockito.mock(RefreshTokenRepository.class);
        RefreshTokenBusinessRules refreshTokenBusinessRules = new RefreshTokenBusinessRules();
        JwtService jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", "mySuperSecretKeymySuperSecretKeymySuperSecretKey");
        ReflectionTestUtils.setField(jwtService, "expiration", 3600000L);
        refreshTokenService = new RefreshTokenManager(refreshTokenRepository, refreshTokenBusinessRules, jwtService);
        ReflectionTestUtils.setField(refreshTokenService, "refreshTokenExpiryDays", 10);
    }

    @Test
    void create_shouldBeSuccess() {
        RefreshToken refreshTokenToCreate = new RefreshToken();
        Mockito.when(refreshTokenRepository.save(Mockito.any())).thenReturn(refreshTokenToCreate);
        RefreshToken createdRefreshToken = refreshTokenService.create(new User());
        assertEquals(refreshTokenToCreate, createdRefreshToken);
    }

    @Test
    void verify_withNotExistingRefreshToken_shouldThrowException() {
        Mockito.when(refreshTokenRepository.findByToken(Mockito.any(String.class))).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> {
            refreshTokenService.verify("token");
        });
    }

    @Test
    void verify_withExpiredRefreshToken_shouldThrowException() {
        RefreshToken expiredRefreshToken = new RefreshToken();
        expiredRefreshToken.setExpirationDate(LocalDateTime.now().minusDays(1));
        Mockito.when(refreshTokenRepository.findByToken(Mockito.any(String.class))).thenReturn(Optional.of(expiredRefreshToken));
        assertThrows(BusinessException.class, () -> {
            refreshTokenService.verify("token");
        });
    }

    @Test
    void verify_withRevokedRefreshToken_shouldThrowException() {
        RefreshToken revokedRefreshToken = new RefreshToken();
        revokedRefreshToken.setExpirationDate(LocalDateTime.now().plusDays(1));
        revokedRefreshToken.setRevokedDate(LocalDateTime.now());
        Mockito.when(refreshTokenRepository.findByToken(Mockito.any(String.class))).thenReturn(Optional.of(revokedRefreshToken));
        assertThrows(BusinessException.class, () -> {
            refreshTokenService.verify("token");
        });
    }

    @Test
    void rotate_shouldBeSuccess() {
        RefreshToken tokenToRotate = new RefreshToken();
        tokenToRotate.setUser(new User());
        Mockito.when(refreshTokenRepository.save(Mockito.any())).thenReturn(new RefreshToken());
        RefreshToken rotatedToken = refreshTokenService.rotate(tokenToRotate, "ip");
        assertNotNull(rotatedToken);
    }

    @Test
    void revokeOldTokens_shouldBeSuccess() {
        User user = new User();
        RefreshToken oldToken = new RefreshToken();
        oldToken.setExpirationDate(LocalDateTime.now().minusDays(1));
        Mockito
                .when(refreshTokenRepository.findAllByUserAndRevokedDateIsNullAndExpirationDateBefore(Mockito.any(User.class), Mockito.any(LocalDateTime.class)))
                .thenReturn(List.of(oldToken));
        Mockito.when(refreshTokenRepository.saveAll(Mockito.any())).thenReturn(List.of(new RefreshToken()));
        refreshTokenService.revokeOldTokens(user, "ip");
        assertTrue(true);
    }
}