package com.turkcell.crm.search_service.kafka.consumers;

import com.turkcell.crm.common.shared.kafka.events.CustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.CustomerDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.CustomerUpdatedEvent;
import com.turkcell.crm.search_service.business.abstracts.CustomerSearchService;
import com.turkcell.crm.search_service.business.mappers.CustomerMapper;
import com.turkcell.crm.search_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerConsumer {
    private final CustomerMapper customerMapper;
    private final CustomerSearchService customerSearchService;

    @KafkaListener(topics = "customer-created", groupId = "customer.group")
    public void consume(CustomerCreatedEvent customerCreatedEvent) {
        Customer customer = customerMapper.toCustomer(customerCreatedEvent);
        this.customerSearchService.add(customer);
    }

    @KafkaListener(topics = "customer-updated", groupId = "customer.group")
    public void consume(CustomerUpdatedEvent customerUpdatedEvent) {
        Customer customer = customerMapper.toCustomer(customerUpdatedEvent);
        this.customerSearchService.update(customer);
    }

    @KafkaListener(topics = "customer-deleted", groupId = "customer.group")
    public void consume(CustomerDeletedEvent customerDeletedEvent) {
        this.customerSearchService.delete(customerDeletedEvent.getId());
    }
}
