package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.customer_service.business.constants.messages.Messages;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.customer_service.data_access.abstracts.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityBusinessRules {
    private final MessageService messageService;
    private final CityRepository cityRepository;

    public void cityShouldBeExist(int id) {
        boolean exists = this.cityRepository.existsById(id);
        if (!exists) {
            throw new BusinessException(this.messageService.getMessage(Messages.CityMessages.NOT_FOUND));
        }
    }
}
