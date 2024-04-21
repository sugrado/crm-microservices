package com.turkcell.crm.identityService.business.abstracts;

import com.turkcell.crm.identityService.business.dtos.requests.auth.LoginRequest;
import com.turkcell.crm.identityService.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.crm.identityService.business.dtos.responses.auth.LoggedInResponse;
import com.turkcell.crm.identityService.business.dtos.responses.auth.RefreshedTokenResponse;
import com.turkcell.crm.identityService.business.dtos.responses.auth.RegisteredResponse;

public interface AuthService {
    LoggedInResponse login(LoginRequest loginRequest, String ipAddress);

    RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress);

    RegisteredResponse register(RegisterRequest request);
}
