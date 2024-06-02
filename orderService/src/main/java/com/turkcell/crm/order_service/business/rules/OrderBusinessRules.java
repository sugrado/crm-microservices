package com.turkcell.crm.order_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.order_service.business.constants.Messages;
import com.turkcell.crm.order_service.core.business.abstracts.MessageService;
import com.turkcell.crm.order_service.entities.concretes.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderBusinessRules {
    private final MessageService messageService;

    public void orderShouldBeExist(Optional<Order> optionalOrder) {
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.OrderMessages.ORDER_NOT_FOUND));
        }
    }
}
