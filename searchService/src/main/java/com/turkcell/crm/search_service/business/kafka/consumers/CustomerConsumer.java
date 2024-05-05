package com.turkcell.crm.search_service.business.kafka.consumers;

import com.turkcell.crm.common.kafka.events.CustomerCreatedEvent;
import com.turkcell.crm.common.kafka.events.CustomerDeletedEvent;
import com.turkcell.crm.common.kafka.events.CustomerUpdatedEvent;
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
        customerSearchService.add(customer);
    }

    @KafkaListener(topics = "customer-updated", groupId = "customer.group")
    public void consume(CustomerUpdatedEvent customerUpdatedEvent) {
        Customer customer = customerMapper.toCustomer(customerUpdatedEvent);
        customerSearchService.update(customer);
    }

    @KafkaListener(topics = "customer-deleted", groupId = "customer.group")
    public void consume(CustomerDeletedEvent customerDeletedEvent) {
        customerSearchService.delete(customerDeletedEvent.getId());
    }
}
