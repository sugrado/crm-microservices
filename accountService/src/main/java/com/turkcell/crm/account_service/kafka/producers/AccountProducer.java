package com.turkcell.crm.account_service.kafka.producers;

import com.turkcell.crm.common.shared.kafka.events.accounts.AccountDeletedEvent;
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
public class AccountProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AccountProducer.class);

    public void send(AccountDeletedEvent accountDeletedEvent) {

        logger.info("Account deleted event sent to Kafka: {}", accountDeletedEvent);

        Message<AccountDeletedEvent> message = MessageBuilder.withPayload(accountDeletedEvent)
                .setHeader(KafkaHeaders.TOPIC, "account-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}
