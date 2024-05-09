package com.turkcell.crm.identity_service.business.dtos.responses.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisteredResponse {
    private String accessToken;
    private String refreshToken;
}
