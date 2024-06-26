package com.turkcell.crm.identity_service.business.dtos.responses.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInResponse {
    private String accessToken;
    private String refreshToken;
}
