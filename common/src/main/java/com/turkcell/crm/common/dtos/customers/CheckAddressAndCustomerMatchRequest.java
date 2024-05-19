package com.turkcell.crm.common.dtos.customers;

public record CheckAddressAndCustomerMatchRequest(
        int customerId,
        int addressId) {
}
