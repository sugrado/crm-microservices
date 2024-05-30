package com.turkcell.crm.order_service.kafka;


import com.turkcell.crm.common.shared.kafka.events.OrderCreatedEvent;
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

    public void send(OrderCreatedEvent orderCreatedEvent) {

        logger.info("Order created event sent to Kafka: {}", orderCreatedEvent);

        Message<OrderCreatedEvent> message = MessageBuilder.withPayload(orderCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "order-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}
