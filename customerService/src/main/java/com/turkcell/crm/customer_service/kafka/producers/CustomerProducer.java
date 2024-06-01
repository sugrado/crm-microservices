package com.turkcell.crm.customer_service.kafka.producers;

import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerUpdatedEvent;
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

    public void send(IndividualCustomerCreatedEvent individualCustomerCreatedEvent) {

        logger.info("Customer created event sent to Kafka: {}", individualCustomerCreatedEvent);

        Message<IndividualCustomerCreatedEvent> message = MessageBuilder.withPayload(individualCustomerCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "customer-created")
                .build();
        kafkaTemplate.send(message);
    }

    public void send(IndividualCustomerUpdatedEvent individualCustomerUpdatedEvent) {

        logger.info("Customer updated event sent to Kafka: {}", individualCustomerUpdatedEvent);

        Message<IndividualCustomerUpdatedEvent> message = MessageBuilder.withPayload(individualCustomerUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "customer-updated")
                .build();

        kafkaTemplate.send(message);
    }

    public void send(IndividualCustomerDeletedEvent individualCustomerDeletedEvent) {

        logger.info("Customer deleted event sent to Kafka: {}", individualCustomerDeletedEvent);

        Message<IndividualCustomerDeletedEvent> message = MessageBuilder.withPayload(individualCustomerDeletedEvent)
                .setHeader(KafkaHeaders.TOPIC, "customer-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}
