package com.turkcell.crm.basket_service.kafka.consumers;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.common.shared.kafka.events.accounts.AccountDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountConsumer {
    private final BasketService basketService;

    @KafkaListener(topics = "account-deleted", groupId = "basket.group")
    public void consume(AccountDeletedEvent accountDeletedEvent) {
        String accountId = Integer.toString(accountDeletedEvent.getId());
        this.basketService.emptyBasket(accountId);
    }
}
