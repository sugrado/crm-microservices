package com.turkcell.crm.identity_service.business.dtos.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record RegisterRequest(
        @Email
        @NotNull
        String email,

        @NotNull
        @Size(min = 8, max = 16)
        String password
) {
}
