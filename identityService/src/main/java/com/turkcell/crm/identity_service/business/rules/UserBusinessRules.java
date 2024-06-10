package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.AuthenticationException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.core.business.abstracts.MessageService;
import com.turkcell.crm.identity_service.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final MessageService messageService;

    public void userShouldBeExist(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new AuthenticationException(messageService.getMessage(Messages.AuthMessages.LOGIN_FAILED));
        }
    }

    public void userShouldBeExists(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.UserMessages.USER_NOT_FOUND));
        }
    }
}
