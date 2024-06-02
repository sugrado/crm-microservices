package com.turkcell.crm.identity_service.business.constants;

public class Messages {
    public static class AuthMessages {
        public static String LOGIN_FAILED = "loginFailed";
        public static String USER_MAIL_ALREADY_EXISTS = "userMailAlreadyExists";
        public static String USER_ROLE_CACHE_NOT_FOUND = "userRoleCacheNotFound";
        public static String ROLES_NOT_MATCH = "rolesNotMatch";
    }

    public static class RefreshTokenMessages {
        public static final String REFRESH_TOKEN_NOT_FOUND = "refreshTokenNotFound";
        public static final String REFRESH_TOKEN_EXPIRED = "refreshTokenExpired";
        public static final String REFRESH_TOKEN_REVOKED = "refreshTokenRevoked";
    }

    public static class UserMessages {
        public static final String USER_NOT_FOUND = "userNotFound";
    }

    public static class RoleMessages {
        public static final String ROLE_NOT_FOUND = "roleNotFound";
    }

    public static class UserRoleMessages {
        public static final String USER_DOES_NOT_HAVE_ROLE = "userDoesNotHaveRole";
        public static final String USER_ALREADY_HAS_ROLE = "userAlreadyHasRole";
    }
}
