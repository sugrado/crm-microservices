package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.common.exceptions.types.NotFoundException;
import com.turkcell.crm.customer_service.business.constants.messages.Messages;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.data_access.abstracts.CityRepository;
import com.turkcell.crm.customer_service.entities.concretes.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityBusinessRules {
    private final MessageService messageService;
    private final CityRepository cityRepository;

    public void cityShouldBeExist(Optional<City> city) {
        if (city.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.CityMessages.NOT_FOUND));
        }
    }
}
