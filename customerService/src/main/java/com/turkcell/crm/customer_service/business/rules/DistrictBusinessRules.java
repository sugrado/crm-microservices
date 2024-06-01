package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.customer_service.business.constants.Messages;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.entities.concretes.District;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictBusinessRules {
    private final MessageService messageService;

    public void districtShouldBeExist(Optional<District> districts) {
        if (districts.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.DistrictMessages.NOT_FOUND));
        }
    }
}
