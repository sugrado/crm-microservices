package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.identity_service.business.abstracts.UserRoleCacheService;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRoleRedisRepository;
import com.turkcell.crm.identity_service.entities.concretes.UserRoleCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleCacheManager implements UserRoleCacheService {
    private final UserRoleRedisRepository userRoleRedisRepository;

    @Override
    public void addToUser(String username, String role) {
        UserRoleCache userRoleCache;
        Optional<UserRoleCache> userRoleCacheOptional = this.getByUsername(username);
        userRoleCache = userRoleCacheOptional.orElseGet(() -> new UserRoleCache(username));
        userRoleCache.getRoles().add(role);
        this.userRoleRedisRepository.addOrUpdate(userRoleCache);
    }

    @Override
    public void removeFromUser(String username, String role) {
        Optional<UserRoleCache> userRoleCacheOptional = this.userRoleRedisRepository.getById(username);
        if (userRoleCacheOptional.isEmpty()) {
            return;
        }

        UserRoleCache userRoleCache = userRoleCacheOptional.get();
        if (!userRoleCache.getRoles().contains(role)) {
            return;
        }

        Optional<String> item = userRoleCache.getRoles()
                .stream()
                .filter(p -> p.equals(role))
                .findFirst();

        if (item.isEmpty()) {
            return;
        }

        userRoleCache.getRoles().remove(item.get());
        this.userRoleRedisRepository.addOrUpdate(userRoleCache);
    }

    @Override
    public void clearUserRoles(String username) {
        Optional<UserRoleCache> userRoleCacheOptional = this.userRoleRedisRepository.getById(username);
        if (userRoleCacheOptional.isEmpty()) {
            return;
        }
        UserRoleCache userRoleCache = userRoleCacheOptional.get();
        this.userRoleRedisRepository.delete(userRoleCache.getUsername());
        userRoleCache = new UserRoleCache(username);
        this.userRoleRedisRepository.addOrUpdate(userRoleCache);
    }

    @Override
    public Optional<UserRoleCache> getByUsername(String username) {
        return this.userRoleRedisRepository.getById(username);
    }
}
