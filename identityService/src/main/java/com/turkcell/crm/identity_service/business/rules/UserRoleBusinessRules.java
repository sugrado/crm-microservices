package com.turkcell.crm.identity_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.identity_service.business.constants.Messages;
import com.turkcell.crm.identity_service.core.business.abstracts.MessageService;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRoleRepository;
import com.turkcell.crm.identity_service.entities.concretes.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleBusinessRules {
    private final UserRoleRepository userRoleRepository;
    private final MessageService messageService;

    public void userShouldHaveRole(Optional<UserRole> userRole) {
        if (userRole.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.UserRoleMessages.USER_DOES_NOT_HAVE_ROLE));
        }
    }

    public void userShouldNotHaveRole(int userId, int roleId) {
        if (this.userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
            throw new NotFoundException(messageService.getMessage(Messages.UserRoleMessages.USER_ALREADY_HAS_ROLE));
        }
    }
}
