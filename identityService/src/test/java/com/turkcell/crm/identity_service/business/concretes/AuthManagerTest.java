package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.core.services.JwtService;
import com.turkcell.crm.identity_service.business.abstracts.AuthService;
import com.turkcell.crm.identity_service.business.abstracts.RefreshTokenService;
import com.turkcell.crm.identity_service.business.abstracts.SecurityContextService;
import com.turkcell.crm.identity_service.business.abstracts.UserRoleCacheService;
import com.turkcell.crm.identity_service.business.dtos.requests.auth.LoginRequest;
import com.turkcell.crm.identity_service.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.LoggedInResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.RefreshedTokenResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.RegisteredResponse;
import com.turkcell.crm.identity_service.business.mappers.AuthMapper;
import com.turkcell.crm.identity_service.business.mappers.AuthMapperImpl;
import com.turkcell.crm.identity_service.business.mappers.UserMapper;
import com.turkcell.crm.identity_service.business.mappers.UserMapperImpl;
import com.turkcell.crm.identity_service.business.rules.AuthBusinessRules;
import com.turkcell.crm.identity_service.business.rules.UserBusinessRules;
import com.turkcell.crm.identity_service.core.business.abstracts.MessageService;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRepository;
import com.turkcell.crm.identity_service.entities.concretes.RefreshToken;
import com.turkcell.crm.identity_service.entities.concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthManagerTest {
    private AuthService authService;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private RefreshTokenService refreshTokenService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        authenticationManager = mock(AuthenticationManager.class);
        MessageService messageService = mock(MessageService.class);
        AuthBusinessRules authBusinessRules = new AuthBusinessRules(userRepository, authenticationManager, messageService);

        UserBusinessRules userBusinessRules = new UserBusinessRules(messageService);
        UserMapper userMapper = new UserMapperImpl();
        UserManager userManager = new UserManager(userRepository, userBusinessRules, userMapper);

        JwtService jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", "mySuperSecretKeymySuperSecretKeymySuperSecretKey");
        ReflectionTestUtils.setField(jwtService, "expiration", 3600000L);
        refreshTokenService = mock(RefreshTokenManager.class);

        passwordEncoder = mock(PasswordEncoder.class);
        AuthMapper authMapper = new AuthMapperImpl();
        SecurityContextService securityContextService = mock(SecurityContextService.class);
        UserRoleCacheService userRoleCacheService = mock(UserRoleCacheService.class);
        authService = new AuthManager(authBusinessRules, userManager, refreshTokenService, passwordEncoder, jwtService, authMapper, userRoleCacheService, securityContextService);
    }

    @Test
    void register_withExistingEmail_shouldThrowException() {
        RegisterRequest registerRequest = new RegisterRequest("gorkem@mail.com", "123456789");
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        assertThrows(BusinessException.class, () -> {
            authService.register(registerRequest);
        });
    }

    @Test
    void register_shouldBeSuccess() {
        RegisterRequest registerRequest = new RegisterRequest("gorkem@mail.com", "123456789");
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        when(passwordEncoder.encode(Mockito.anyString())).thenReturn("123456789");
        when(userRepository.save(Mockito.any())).thenReturn(new User());
        when(refreshTokenService.create(Mockito.any())).thenReturn(new RefreshToken());

        RegisteredResponse res = authService.register(registerRequest);

        assertNotNull(res.getAccessToken());
    }

    @Test
    void login_withWrongEmail_shouldThrowException() {
        LoginRequest loginRequest = new LoginRequest("gorkem@mail.com", "123456789");
        when(authenticationManager.authenticate(Mockito.any())).thenThrow(new InternalAuthenticationServiceException(""));
        assertThrows(InternalAuthenticationServiceException.class, () -> {
            authService.login(loginRequest, "0.0.0.0");
        });
    }

    @Test
    void login_withWrongPassword_shouldThrowException() {
        LoginRequest loginRequest = new LoginRequest("gorkem@mail.com", "123456789");
        when(authenticationManager.authenticate(Mockito.any())).thenThrow(new BadCredentialsException(""));
        assertThrows(BadCredentialsException.class, () -> {
            authService.login(loginRequest, "0.0.0.0");
        });
    }

    @Test
    void login_shouldBeSuccess() {
        LoginRequest loginRequest = new LoginRequest("gorkem@mail.com", "123456789");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new User()));
        when(refreshTokenService.create(Mockito.any())).thenReturn(new RefreshToken());

        LoggedInResponse res = authService.login(loginRequest, "0.0.0.0");

        assertNotNull(res.getAccessToken());
    }

    @Test
    void refreshToken_shouldBeSuccess() {
        RefreshToken verifiedToken = new RefreshToken();
        verifiedToken.setUser(new User());

        when(refreshTokenService.verify(Mockito.anyString())).thenReturn(verifiedToken);
        when(refreshTokenService.rotate(Mockito.any(), Mockito.anyString())).thenReturn(new RefreshToken());
        RefreshedTokenResponse res = authService.refreshToken("token", "0.0.0.0");

        assertNotNull(res.getAccessToken());
    }
}