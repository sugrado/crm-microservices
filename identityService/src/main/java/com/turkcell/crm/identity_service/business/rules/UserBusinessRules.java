package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.AuthenticationException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRepository;
import com.turkcell.crm.identity_service.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    public void userShouldBeExist(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new AuthenticationException(Messages.AuthMessages.LOGIN_FAILED);
        }
    }

    public void userShouldBeExists(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new NotFoundException(Messages.UserMessages.USER_NOT_FOUND);
        }
    }
}
