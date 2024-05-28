package com.turkcell.crm.basket_service.kafka;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.common.kafka.events.CustomerDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerConsumer {
    private final BasketService basketService;
    @KafkaListener(topics = "customer-deleted", groupId = "customer.group.basket")
    public void consume(CustomerDeletedEvent customerDeletedEvent) {
        String customerId = Integer.toString(customerDeletedEvent.getId());
        this.basketService.emptyBasket(customerId);
    }
}
