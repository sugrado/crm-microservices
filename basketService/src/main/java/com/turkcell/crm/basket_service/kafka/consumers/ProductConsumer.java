package com.turkcell.crm.basket_service.kafka.consumers;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.common.shared.kafka.events.catalogs.ProductDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductConsumer {
    private final BasketService basketService;

    @KafkaListener(topics = "product-deleted", groupId = "basket.group")
    public void consume(ProductDeletedEvent productDeletedEvent) {
        this.basketService.removeProductFromBaskets(productDeletedEvent.getId());
    }
}
