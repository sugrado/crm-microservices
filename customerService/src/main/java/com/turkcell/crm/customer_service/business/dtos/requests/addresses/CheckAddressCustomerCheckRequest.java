package com.turkcell.crm.customer_service.business.dtos.requests.addresses;

public record CheckAddressCustomerCheckRequest(
        int customerId,
        int addressId
) {
}
