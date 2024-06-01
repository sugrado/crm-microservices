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

    public static class UserMessages {
        public static final String USER_NOT_FOUND = "User not found";
    }

    public static class RoleMessages {
        public static final String ROLE_NOT_FOUND = "Role not found";
    }

    public static class UserRoleMessages {
        public static final String USER_DOES_NOT_HAVE_ROLE = "User does not have this role";
        public static final String USER_ALREADY_HAS_ROLE = "User already has this role";
    }
}
