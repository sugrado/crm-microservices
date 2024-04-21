package com.turkcell.crm.identityService.business.dtos.responses.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisteredResponse {
    private String accessToken;
    private String refreshToken;
}
