package com.turkcell.crm.identity_service.business.dtos.responses.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RefreshedTokenResponse {
    private String accessToken;
    private String refreshToken;
}
