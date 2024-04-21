package com.turkcell.crm.identityService.business.rules;

import com.turkcell.crm.identityService.business.constants.Messages;
import com.turkcell.crm.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.identityService.data_access.abstracts.UserRepository;
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
            throw new BusinessException(Messages.AuthMessages.LOGIN_FAILED);
        }
    }
}
