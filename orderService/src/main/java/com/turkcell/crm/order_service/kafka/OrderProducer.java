package com.turkcell.crm.order_service.kafka;


import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
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
public class OrderProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    public void send(IndividualOrderCreatedEvent individualOrderCreatedEvent) {

        logger.info("Individual order created event sent to Kafka: {}", individualOrderCreatedEvent);

        Message<IndividualOrderCreatedEvent> message = MessageBuilder.withPayload(individualOrderCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "individual-order-created")
                .build();

        kafkaTemplate.send(message);
    }
}
