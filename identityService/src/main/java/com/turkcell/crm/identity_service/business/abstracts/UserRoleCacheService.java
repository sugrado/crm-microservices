package com.turkcell.crm.identity_service.business.abstracts;

import com.turkcell.crm.identity_service.entities.concretes.UserRoleCache;

import java.util.Optional;

public interface UserRoleCacheService {
    void addToUser(String username, String role);

    void removeFromUser(String username, String role);

    void clearUserRoles(String username);

    Optional<UserRoleCache> getByUsername(String username);
}
