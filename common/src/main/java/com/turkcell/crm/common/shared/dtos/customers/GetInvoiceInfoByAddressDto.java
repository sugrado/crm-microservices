package com.turkcell.crm.common.shared.dtos.customers;

public record GetInvoiceInfoByAddressDto(
        String street,
        String houseFlatNumber,
        String description,
        String cityName,
        String districtName
) {
}
