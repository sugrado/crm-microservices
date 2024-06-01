package com.turkcell.crm.identity_service.data_access.concretes;

import com.turkcell.crm.identity_service.data_access.abstracts.UserRoleRedisRepository;
import com.turkcell.crm.identity_service.entities.concretes.UserRoleCache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleRedisRepositoryImpl implements UserRoleRedisRepository {
    public static final String KEY = "UserRoles";
    private final HashOperations<String, String, UserRoleCache> hashOperations;

    public UserRoleRedisRepositoryImpl(RedisTemplate<String, UserRoleCache> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Optional<UserRoleCache> getById(String id) {
        UserRoleCache userRoleCache = this.hashOperations.get(KEY, id);
        return userRoleCache == null ? Optional.empty() : Optional.of(userRoleCache);
    }

    @Override
    public void addOrUpdate(UserRoleCache userRoleCache) {
        this.hashOperations.put(KEY, userRoleCache.getUsername(), userRoleCache);
    }

    @Override
    public void delete(String id) {
        this.hashOperations.delete(KEY, id);
    }
}
