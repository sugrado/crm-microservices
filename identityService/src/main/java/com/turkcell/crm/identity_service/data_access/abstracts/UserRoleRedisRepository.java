package com.turkcell.crm.identity_service.data_access.abstracts;

import com.turkcell.crm.identity_service.entities.concretes.UserRoleCache;

import java.util.Optional;

public interface UserRoleRedisRepository {
    Optional<UserRoleCache> getById(String id);

    void addOrUpdate(UserRoleCache userRoleCache);

    void delete(String id);
}
