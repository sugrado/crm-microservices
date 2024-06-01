package com.turkcell.crm.basket_service.kafka.consumers;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final BasketService basketService;

    @KafkaListener(topics = "individual-order-created", groupId = "basket.group")
    public void consume(IndividualOrderCreatedEvent individualOrderCreatedEvent) {
        String accountId = Integer.toString(individualOrderCreatedEvent.getAccountId());
        this.basketService.emptyBasket(accountId);
    }
}
