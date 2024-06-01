package com.turkcell.crm.search_service.kafka.consumers;

import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerUpdatedEvent;
import com.turkcell.crm.search_service.business.abstracts.IndividualCustomerSearchService;
import com.turkcell.crm.search_service.business.mappers.IndividualCustomerMapper;
import com.turkcell.crm.search_service.entities.concretes.IndividualCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualCustomerConsumer {
    private final IndividualCustomerMapper customerMapper;
    private final IndividualCustomerSearchService customerSearchService;

    @KafkaListener(topics = "customer-created", groupId = "search.group")
    public void consume(IndividualCustomerCreatedEvent individualCustomerCreatedEvent) {
        IndividualCustomer customer = customerMapper.toCustomer(individualCustomerCreatedEvent);
        this.customerSearchService.add(customer);
    }

    @KafkaListener(topics = "customer-updated", groupId = "search.group")
    public void consume(IndividualCustomerUpdatedEvent individualCustomerUpdatedEvent) {
        IndividualCustomer customer = customerMapper.toCustomer(individualCustomerUpdatedEvent);
        this.customerSearchService.update(customer);
    }

    @KafkaListener(topics = "customer-deleted", groupId = "search.group")
    public void consume(IndividualCustomerDeletedEvent individualCustomerDeletedEvent) {
        this.customerSearchService.delete(individualCustomerDeletedEvent.getId());
    }
}
