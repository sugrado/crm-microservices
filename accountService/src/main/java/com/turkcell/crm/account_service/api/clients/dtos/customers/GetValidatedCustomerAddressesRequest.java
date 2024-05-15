package com.turkcell.crm.account_service.api.clients.dtos.customers;

import java.util.List;

public record GetValidatedCustomerAddressesRequest(
        int customerId,
        List<Integer> addressIds
) {
}
