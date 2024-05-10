package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.identity_service.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    public void userShouldBeExist(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new BusinessException(Messages.AuthMessages.LOGIN_FAILED);
        }
    }
}
