package com.turkcell.crm.account_service.kafka.producers;

import com.turkcell.crm.common.kafka.events.AccountDeletedEvent;
import com.turkcell.crm.common.kafka.events.CustomerDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public void send(AccountDeletedEvent accountDeletedEvent) {

        System.out.println("Account deleted event sent to Kafka: " + accountDeletedEvent);

        Message<AccountDeletedEvent> message = MessageBuilder.withPayload(accountDeletedEvent)
                .setHeader(KafkaHeaders.TOPIC, "account-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}
