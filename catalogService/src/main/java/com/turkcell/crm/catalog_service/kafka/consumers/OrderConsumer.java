package com.turkcell.crm.catalog_service.kafka.consumers;

import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final ProductService productService;

    @KafkaListener(topics = "individual-order-created", groupId = "catalog.group")
    public void consume(IndividualOrderCreatedEvent individualOrderCreatedEvent) {
        productService.decreaseStocks(individualOrderCreatedEvent.getProducts());
    }
}