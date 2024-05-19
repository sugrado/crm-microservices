package com.turkcell.crm.identity_service.api.controllers;

import com.turkcell.crm.identity_service.business.abstracts.AuthService;
import com.turkcell.crm.identity_service.business.dtos.requests.auth.LoginRequest;
import com.turkcell.crm.identity_service.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.LoggedInResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.RefreshedTokenResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.auth.RegisteredResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/identity-service/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthService authService;

    @Value("${refreshToken.cookie-key}")
    private String refreshTokenCookieKey;
    @Value("${refreshToken.expiration.days}")
    private int refreshTokenExpirationDays;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request, HttpServletResponse response) {
        RegisteredResponse registeredResponse = authService.register(request);
        setCookie(refreshTokenCookieKey, registeredResponse.getRefreshToken(), calculateCookieExpirationSeconds(refreshTokenExpirationDays), response);
        return registeredResponse.getAccessToken();
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) {
        LoggedInResponse loggedInResponse = authService.login(loginRequest, getIpAddress(request));
        setCookie(refreshTokenCookieKey, loggedInResponse.getRefreshToken(), calculateCookieExpirationSeconds(refreshTokenExpirationDays), response);
        return loggedInResponse.getAccessToken();
    }

    @PostMapping("/refresh")
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = getCookie(request, refreshTokenCookieKey);
        RefreshedTokenResponse refreshedTokenResponse = authService.refreshToken(refreshToken, getIpAddress(request));
        setCookie(refreshTokenCookieKey, refreshedTokenResponse.getRefreshToken(), calculateCookieExpirationSeconds(refreshTokenExpirationDays), response);
        return refreshedTokenResponse.getAccessToken();
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
