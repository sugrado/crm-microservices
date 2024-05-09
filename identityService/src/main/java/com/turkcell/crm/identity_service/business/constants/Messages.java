package com.turkcell.crm.identity_service.business.constants;

public class Messages {
    public static class AuthMessages {
        public static String LOGIN_FAILED = "Login failed";
        public static String USER_MAIL_ALREADY_EXISTS = "User mail already exists";
    }

    public static class RefreshTokenMessages {
        public static final String REFRESH_TOKEN_NOT_FOUND = "Refresh token not found";
        public static final String REFRESH_TOKEN_EXPIRED = "Refresh token expired";
        public static final String REFRESH_TOKEN_REVOKED = "Refresh token revoked";
    }
}
