package com.turkcell.crm.common.shared.kafka.events.orders;

public record OrderCreatedEventAddress(
        String street,
        String houseFlatNumber,
        String description,
        String cityName,
        String districtName
) {
}
