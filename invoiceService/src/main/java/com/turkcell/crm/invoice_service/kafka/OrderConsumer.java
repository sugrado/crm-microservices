package com.turkcell.crm.invoice_service.kafka;

import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.invoice_service.business.abstracts.IndividualInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final IndividualInvoiceService individualInvoiceService;

    @KafkaListener(topics = "individual-order-created", groupId = "invoice.group")
    public void consume(IndividualOrderCreatedEvent individualOrderCreatedEvent) {
        this.individualInvoiceService.create(individualOrderCreatedEvent);
    }
}
