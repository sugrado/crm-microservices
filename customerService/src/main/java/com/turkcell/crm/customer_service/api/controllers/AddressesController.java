package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.ChangeDefaultAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.ChangedDefaultAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.CreatedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.DeletedAddressResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer-service/api/v1/addresses")
public class AddressesController {
    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAddressResponse add(@Valid @RequestBody CreateAddressRequest createAddressRequest) {
        return this.addressService.add(createAddressRequest);
    }

    @PatchMapping("default")
    public ChangedDefaultAddressResponse changeDefaultAddress(@RequestBody ChangeDefaultAddressRequest changeDefaultAddressRequest) {
        return this.addressService.changeDefaultAddress(changeDefaultAddressRequest);
    }

    @DeleteMapping("{id}")
    public DeletedAddressResponse delete(@PathVariable int id) {
        return this.addressService.delete(id);
    }
}
