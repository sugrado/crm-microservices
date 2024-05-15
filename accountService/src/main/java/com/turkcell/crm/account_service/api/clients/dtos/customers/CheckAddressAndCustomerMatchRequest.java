package com.turkcell.crm.account_service.api.clients.dtos.customers;

public record CheckAddressAndCustomerMatchRequest(
        int customerId,
        int addressId) {
}
