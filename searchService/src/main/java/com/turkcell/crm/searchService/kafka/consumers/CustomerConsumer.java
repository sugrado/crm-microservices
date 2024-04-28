package com.turkcell.crm.searchService.kafka.consumers;

import com.turkcell.crm.common.events.CustomerCreatedEvent;
import com.turkcell.crm.searchService.business.abstracts.CustomerSearchService;
import com.turkcell.crm.searchService.business.mappers.CustomerMapper;
import com.turkcell.crm.searchService.entities.concretes.Customer;
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
}
