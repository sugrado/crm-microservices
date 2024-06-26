package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.core.business.abstracts.MessageService;
import com.turkcell.crm.identity_service.entities.concretes.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleBusinessRules {
    private final MessageService messageService;

    public void roleShouldBeExists(Optional<Role> roleOptional) {
        if (roleOptional.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.RoleMessages.ROLE_NOT_FOUND));
        }
    }
}
