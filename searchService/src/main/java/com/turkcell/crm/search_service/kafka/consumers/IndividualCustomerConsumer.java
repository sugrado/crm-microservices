package com.turkcell.crm.search_service.kafka.consumers;

import com.turkcell.crm.common.shared.kafka.events.customers.CustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.CustomerDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.CustomerUpdatedEvent;
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
    public void consume(CustomerCreatedEvent customerCreatedEvent) {
        IndividualCustomer customer = customerMapper.toCustomer(customerCreatedEvent);
        this.customerSearchService.add(customer);
    }

    @KafkaListener(topics = "customer-updated", groupId = "search.group")
    public void consume(CustomerUpdatedEvent customerUpdatedEvent) {
        IndividualCustomer customer = customerMapper.toCustomer(customerUpdatedEvent);
        this.customerSearchService.update(customer);
    }

    @KafkaListener(topics = "customer-deleted", groupId = "search.group")
    public void consume(CustomerDeletedEvent customerDeletedEvent) {
        this.customerSearchService.delete(customerDeletedEvent.getId());
    }
}
