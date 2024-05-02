package com.turkcell.crm.customer_service.business.kafka.producers;

import com.turkcell.crm.common.kafka.events.CustomerCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(CustomerCreatedEvent customerCreatedEvent) {
        System.out.println("Customer created event sent to Kafka: " + customerCreatedEvent);
        Message<CustomerCreatedEvent> message = MessageBuilder.withPayload(customerCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "customer-created")
                .build();
        kafkaTemplate.send(message);
    }
}
