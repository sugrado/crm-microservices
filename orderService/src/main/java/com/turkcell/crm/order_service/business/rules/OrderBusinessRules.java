package com.turkcell.crm.order_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.order_service.business.constants.Messages;
import com.turkcell.crm.order_service.entities.concretes.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderBusinessRules {

    public void orderShouldBeExist(Optional<Order> optionalOrder) {
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException(Messages.OrderMessages.ORDER_NOT_FOUND);
        }
    }
}
