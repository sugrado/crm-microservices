package com.turkcell.crm.customer_service.kafka.producers;

import com.turkcell.crm.common.shared.kafka.events.CustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.CustomerDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.CustomerUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProducer {
    private static final Logger logger = LoggerFactory.getLogger(CustomerProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(CustomerCreatedEvent customerCreatedEvent) {

        logger.info("Customer created event sent to Kafka: {}", customerCreatedEvent);

        Message<CustomerCreatedEvent> message = MessageBuilder.withPayload(customerCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "customer-created")
                .build();
        kafkaTemplate.send(message);
    }

    public void send(CustomerUpdatedEvent customerUpdatedEvent) {

        logger.info("Customer updated event sent to Kafka: {}", customerUpdatedEvent);

        Message<CustomerUpdatedEvent> message = MessageBuilder.withPayload(customerUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "customer-updated")
                .build();

        kafkaTemplate.send(message);
    }

    public void send(CustomerDeletedEvent customerDeletedEvent) {

        logger.info("Customer deleted event sent to Kafka: {}", customerDeletedEvent);

        Message<CustomerDeletedEvent> message = MessageBuilder.withPayload(customerDeletedEvent)
                .setHeader(KafkaHeaders.TOPIC, "customer-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}
