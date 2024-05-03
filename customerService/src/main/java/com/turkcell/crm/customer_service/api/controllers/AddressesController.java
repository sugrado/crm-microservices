package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CheckAddressCustomerCheckRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/addresses")
public class AddressesController {
    private final AddressService addressService;

    @PostMapping("check-address-and-customer-match")
    public void checkAddressCustomerMatch(@RequestBody CheckAddressCustomerCheckRequest checkAddressCustomerCheckRequest) {
        addressService.checkAddressAndCustomerMatch(checkAddressCustomerCheckRequest);
    }
}
