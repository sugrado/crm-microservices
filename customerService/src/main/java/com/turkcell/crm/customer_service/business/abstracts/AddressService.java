package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.common.shared.dtos.customers.CheckAddressAndCustomerMatchRequest;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.ChangeDefaultAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.UpdateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.*;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import com.turkcell.crm.customer_service.entities.concretes.Customer;

import java.util.List;

public interface AddressService {
    void add(List<AddressDto> addressDtoList, Customer customer);

    GetByIdAddressResponse getById(int id);

    Address getByIdEntity(int id);

    List<GetValidatedCustomerAddressesListItemDto> getAllByCustomerAndIds(GetValidatedCustomerAddressesRequest request);

    void checkAddressAndCustomerMatch(CheckAddressAndCustomerMatchRequest checkAddressAndCustomerMatchRequest);

    CreatedAddressResponse add(CreateAddressRequest createAddressRequest);

    UpdatedAddressResponse update(int id, UpdateAddressRequest updateAddressRequest);

    DeletedAddressResponse delete(int id);

    ChangedDefaultAddressResponse changeDefaultAddress(ChangeDefaultAddressRequest changeDefaultAddressRequest);

    void checkIfAddressExists(int addressId);
}
