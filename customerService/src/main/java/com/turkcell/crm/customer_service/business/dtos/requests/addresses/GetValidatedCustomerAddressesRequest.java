package com.turkcell.crm.customer_service.business.dtos.requests.addresses;

import java.util.List;

public record GetValidatedCustomerAddressesRequest(
        int customerId,
        List<Integer> addressIds
) {
}
