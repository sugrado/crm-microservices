package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.common.dtos.customers.CheckAddressAndCustomerMatchRequest;
import com.turkcell.crm.common.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.dtos.customers.GetValidatedCustomerAddressesRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.ChangeDefaultAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.ChangedDefaultAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.CreatedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.DeletedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.GetByIdAddressResponse;
import com.turkcell.crm.customer_service.entities.concretes.Customer;

import java.util.List;

public interface AddressService {
    void add(List<AddressDto> addressDtoList, Customer customer);

    GetByIdAddressResponse getById(int id);

    List<GetValidatedCustomerAddressesListItemDto> getAllByCustomerAndIds(GetValidatedCustomerAddressesRequest request);

    void checkAddressAndCustomerMatch(CheckAddressAndCustomerMatchRequest checkAddressAndCustomerMatchRequest);

    CreatedAddressResponse add(CreateAddressRequest createAddressRequest);

    DeletedAddressResponse delete(int id);

    ChangedDefaultAddressResponse changeDefaultAddress(ChangeDefaultAddressRequest changeDefaultAddressRequest);

    void checkIfAddressExists(int addressId);
}
