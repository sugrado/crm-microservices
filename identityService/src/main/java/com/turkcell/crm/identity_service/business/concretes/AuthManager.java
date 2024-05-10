package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.core.services.JwtService;
import com.turkcell.crm.identity_service.business.abstracts.AuthService;
import com.turkcell.crm.identity_service.business.abstracts.RefreshTokenService;
import com.turkcell.crm.identity_service.business.abstracts.UserService;
import com.turkcell.crm.identity_service.business.dtos.requests.auth.LoginRequest;
import com.turkcell.crm.identity_service.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.LoggedInResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.RefreshedTokenResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.RegisteredResponse;
import com.turkcell.crm.identity_service.business.mappers.AuthMapper;
import com.turkcell.crm.identity_service.business.rules.AuthBusinessRules;
import com.turkcell.crm.identity_service.entities.concretes.RefreshToken;
import com.turkcell.crm.identity_service.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final AuthBusinessRules authBusinessRules;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthMapper authMapper;

    @Override
    public RegisteredResponse register(RegisterRequest request) {
        User user = authMapper.toUser(request);
        authBusinessRules.userEmailShouldNotBeExists(user.getEmail());
        String encodedPassword = passwordEncoder.encode(request.password());
        user.setPassword(encodedPassword);
        User createdUser = userService.add(user);
        RefreshToken refreshToken = refreshTokenService.create(createdUser);
        String accessToken = generateJwt(createdUser);
        return new RegisteredResponse(accessToken, refreshToken.getToken());
    }

    @Override
    public LoggedInResponse login(LoginRequest loginRequest, String ipAddress) {
        authBusinessRules.emailAndPasswordShouldBeMatch(loginRequest.email(), loginRequest.password());
        User user = userService.findByUsername(loginRequest.email());
        refreshTokenService.revokeOldTokens(user, ipAddress);
        RefreshToken refreshToken = refreshTokenService.create(user);
        String accessToken = generateJwt(user);
        return new LoggedInResponse(accessToken, refreshToken.getToken());
    }

    @Override
    public RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress) {
        RefreshToken token = refreshTokenService.verify(refreshToken);
        RefreshToken newToken = refreshTokenService.rotate(token, ipAddress);
        refreshTokenService.revokeOldTokens(token.getUser(), ipAddress);
        String accessToken = generateJwt(token.getUser());
        return new RefreshedTokenResponse(accessToken, newToken.getToken());
    }

    private String generateJwt(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        return jwtService.generateToken(user.getEmail(), claims);
    }
}