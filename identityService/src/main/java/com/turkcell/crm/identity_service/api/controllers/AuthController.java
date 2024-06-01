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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestBody @Valid RegisterRequest request, HttpServletResponse response) {
        RegisteredResponse registeredResponse = this.authService.register(request);
        this.setCookie(this.refreshTokenCookieKey, registeredResponse.getRefreshToken(),
                calculateCookieExpirationSeconds(this.refreshTokenExpirationDays), response);
        return registeredResponse.getAccessToken();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) {
        LoggedInResponse loggedInResponse = this.authService.login(loginRequest, getIpAddress(request));
        this.setCookie(this.refreshTokenCookieKey, loggedInResponse.getRefreshToken(),
                calculateCookieExpirationSeconds(this.refreshTokenExpirationDays), response);
        return loggedInResponse.getAccessToken();
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void login(HttpServletResponse response, HttpServletRequest request) {
        String refreshToken = getCookie(request, this.refreshTokenCookieKey);
        this.authService.logout(refreshToken, getIpAddress(request));
        this.clearCookies(request, response);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = getCookie(request, this.refreshTokenCookieKey);
        RefreshedTokenResponse refreshedTokenResponse = this.authService.refreshToken(refreshToken, getIpAddress(request));
        this.setCookie(this.refreshTokenCookieKey, refreshedTokenResponse.getRefreshToken(),
                calculateCookieExpirationSeconds(this.refreshTokenExpirationDays), response);
        return refreshedTokenResponse.getAccessToken();
    }

    @GetMapping("/validate-token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void validateToken(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        this.authService.validateToken(authHeader);
    }
}
