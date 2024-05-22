package com.turkcell.crm.catalog_service.kafka.producers;

import com.turkcell.crm.common.kafka.events.ProductCreatedEvent;
import com.turkcell.crm.common.kafka.events.ProductDeletedEvent;
import com.turkcell.crm.common.kafka.events.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(ProductCreatedEvent productCreatedEvent){

        System.out.println("Product created event sent to Kafka: " + productCreatedEvent);

        Message<ProductCreatedEvent> message = MessageBuilder.withPayload(productCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "product-created")
                .build();

        kafkaTemplate.send(message);
    }

    public void send(ProductUpdatedEvent productUpdatedEvent){

        System.out.println("Product updated event sent to Kafka: " + productUpdatedEvent);
        Message<ProductUpdatedEvent> message = MessageBuilder.withPayload(productUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "product-updated")
                .build();

        kafkaTemplate.send(message);
    }

    public void send(ProductDeletedEvent productDeletedEvent){

        System.out.println("Product deleted event sent to Kafka: " + productDeletedEvent);

        Message<ProductDeletedEvent> message = MessageBuilder.withPayload(productDeletedEvent)
                .setHeader(KafkaHeaders.TOPIC, "product-deleted")
                .build();

        kafkaTemplate.send(message);
    }


}
