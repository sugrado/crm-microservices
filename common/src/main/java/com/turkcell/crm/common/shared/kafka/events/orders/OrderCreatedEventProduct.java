package com.turkcell.crm.common.shared.kafka.events.orders;

public record OrderCreatedEventProduct(
        int id,
        String title,
        double price
) {
}
