package com.turkcell.crm.catalog_service.kafka.producers;

import com.turkcell.crm.common.shared.kafka.events.catalogs.ProductCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.catalogs.ProductDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.catalogs.ProductUpdatedEvent;
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
public class ProductProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProductProducer.class);

    public void send(ProductCreatedEvent productCreatedEvent) {

        logger.info("Product created event sent to Kafka: {}", productCreatedEvent);

        Message<ProductCreatedEvent> message = MessageBuilder.withPayload(productCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "product-created")
                .build();

        kafkaTemplate.send(message);
    }

    public void send(ProductUpdatedEvent productUpdatedEvent) {

        logger.info("Product updated event sent to Kafka: {}", productUpdatedEvent);
        Message<ProductUpdatedEvent> message = MessageBuilder.withPayload(productUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "product-updated")
                .build();

        kafkaTemplate.send(message);
    }

    public void send(ProductDeletedEvent productDeletedEvent) {

        logger.info("Product deleted event sent to Kafka: {}", productDeletedEvent);

        Message<ProductDeletedEvent> message = MessageBuilder.withPayload(productDeletedEvent)
                .setHeader(KafkaHeaders.TOPIC, "product-deleted")
                .build();

        kafkaTemplate.send(message);
    }


}
