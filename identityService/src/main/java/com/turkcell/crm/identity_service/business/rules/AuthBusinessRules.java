package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.AuthenticationException;
import com.turkcell.crm.common.shared.exceptions.types.AuthorizationException;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.core.business.abstracts.MessageService;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRepository;
import com.turkcell.crm.identity_service.entities.concretes.UserRoleCache;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthBusinessRules {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final MessageService messageService;

    public void userEmailShouldNotBeExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(messageService.getMessage(Messages.AuthMessages.USER_MAIL_ALREADY_EXISTS));
        }
    }

    public void emailAndPasswordShouldBeMatch(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (!authentication.isAuthenticated()) {
            throw new AuthenticationException(messageService.getMessage(Messages.AuthMessages.LOGIN_FAILED));
        }
    }

    public void userRoleCacheShouldBeExists(Optional<UserRoleCache> userRoleCacheOptional) {
        if (userRoleCacheOptional.isEmpty()) {
            throw new AuthorizationException(messageService.getMessage(Messages.AuthMessages.USER_ROLE_CACHE_NOT_FOUND));
        }
    }

    public void rolesShouldBeMatch(List<String> rolesFromToken, List<String> rolesFromCache) {
        if (!rolesFromCache.equals(rolesFromToken)) {
            throw new AuthorizationException(messageService.getMessage(Messages.AuthMessages.ROLES_NOT_MATCH));
        }
    }
}
