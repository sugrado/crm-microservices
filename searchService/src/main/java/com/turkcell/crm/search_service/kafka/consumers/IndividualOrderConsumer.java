package com.turkcell.crm.search_service.kafka.consumers;

import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.search_service.business.abstracts.IndividualOrderSearchService;
import com.turkcell.crm.search_service.business.mappers.IndividualOrderMapper;
import com.turkcell.crm.search_service.entities.concretes.IndividualOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualOrderConsumer {

    private final IndividualOrderSearchService orderSearchService;
    private final IndividualOrderMapper orderMapper;

    @KafkaListener(topics = "order-created", groupId = "search.group")
    public void consume(IndividualOrderCreatedEvent individualOrderCreatedEvent) {
        IndividualOrder order = this.orderMapper.toOrder(individualOrderCreatedEvent);
        this.orderSearchService.add(order);
    }
}
