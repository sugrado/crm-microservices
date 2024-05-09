package com.turkcell.crm.customer_service.business.dtos.requests.addresses;

public record CheckAddressAndCustomerMatchRequest(
        int customerId,
        int addressId
) {
}
