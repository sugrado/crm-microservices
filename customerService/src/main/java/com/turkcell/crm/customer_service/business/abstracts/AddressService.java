package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.entities.concretes.Customer;

import java.util.List;

public interface AddressService {
    void add(List<AddressDto> addressDtoList, Customer customer);
}
