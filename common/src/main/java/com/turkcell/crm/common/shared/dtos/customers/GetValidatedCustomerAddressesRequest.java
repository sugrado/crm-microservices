package com.turkcell.crm.common.shared.dtos.customers;

import java.util.List;

public record GetValidatedCustomerAddressesRequest(
        int customerId,
        List<Integer> addressIds
) {
}
