package com.turkcell.crm.identityService.api.controllers;

import com.turkcell.crm.identityService.business.abstracts.AuthService;
import com.turkcell.crm.identityService.business.dtos.requests.auth.LoginRequest;
import com.turkcell.crm.identityService.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.crm.identityService.business.dtos.responses.auth.LoggedInResponse;
import com.turkcell.crm.identityService.business.dtos.responses.auth.RefreshedTokenResponse;
import com.turkcell.crm.identityService.business.dtos.responses.auth.RegisteredResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identity-service/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthService authService;

    @Value("${jwt.refresh.cookie-key}")
    private String refreshTokenKey;
    @Value("${jwt.refresh.days}")
    private int refreshTokenExpiryDays;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        RegisteredResponse registeredResponse = authService.register(request);
        setCookie(refreshTokenKey, registeredResponse.getRefreshToken(), refreshTokenExpiryDays * 24 * 60 * 60, response);
        return registeredResponse.getAccessToken();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) {
        LoggedInResponse loggedInResponse = authService.login(loginRequest, getIpAddress(request));
        setCookie(refreshTokenKey, loggedInResponse.getRefreshToken(), refreshTokenExpiryDays * 24 * 60 * 60, response);
        return loggedInResponse.getAccessToken();
    }

    @PostMapping("/refresh")
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = getCookie(request, refreshTokenKey);
        RefreshedTokenResponse refreshedTokenResponse = authService.refreshToken(refreshToken, getIpAddress(request));
        setCookie(refreshTokenKey, refreshedTokenResponse.getRefreshToken(), refreshTokenExpiryDays * 24 * 60 * 60, response);
        return refreshedTokenResponse.getAccessToken();
    }
}
