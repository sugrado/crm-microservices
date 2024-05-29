package com.turkcell.crm.common.shared.dtos.customers;

public record CheckAddressAndCustomerMatchRequest(
        int customerId,
        int addressId) {
}
