package com.turkcell.crm.account_service.api.clients;

import com.turkcell.crm.common.shared.dtos.customers.CheckAddressAndCustomerMatchRequest;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "customerService", path = "customer-service/api/v1")
public interface CustomerClient {
    @GetMapping("/customers/check-if-customer-exists/{customerId}")
    void checkIfCustomerExists(@PathVariable int customerId);

    @PostMapping("/addresses/check-address-and-customer-match")
    void checkAddressAndCustomerMatch(@RequestBody CheckAddressAndCustomerMatchRequest checkAddressAndCustomerMatchRequest);

    @GetMapping("/addresses/check-if-address-exists/{addressId}")
    void checkIfAddressExists(@PathVariable int addressId);

    @PostMapping("/addresses/validated-customer-addresses")
    List<GetValidatedCustomerAddressesListItemDto> getValidatedCustomerAddresses(@RequestBody GetValidatedCustomerAddressesRequest getValidatedCustomerAddressesRequest);
}
