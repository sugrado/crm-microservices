package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.CreatedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.DeletedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.UpdatedDefaultAdressStateResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/addresses")
public class AddressesController {
    private AddressService addressService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAddressResponse add(@Valid @RequestBody CreateAddressRequest createAddressRequest){
        return this.addressService.add(createAddressRequest);
    }

    @PatchMapping("/update/{id}")
    public UpdatedDefaultAdressStateResponse updateDefaultAddressState (@PathVariable int id){
        return this.addressService.updateDefaultAddressState(id);
    }

    @DeleteMapping("/delete/{id}")
    public DeletedAddressResponse delete(@PathVariable int id){
        return this.addressService.delete(id);
    }
}
