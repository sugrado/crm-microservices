package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.common.exceptions.types.AuthenticationException;
import com.turkcell.crm.common.exceptions.types.BusinessException;
import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthBusinessRules {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public void userEmailShouldNotBeExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(Messages.AuthMessages.USER_MAIL_ALREADY_EXISTS);
        }
    }

    public void emailAndPasswordShouldBeMatch(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (!authentication.isAuthenticated()) {
            throw new AuthenticationException(Messages.AuthMessages.LOGIN_FAILED);
        }
    }
}
