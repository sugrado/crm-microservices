package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.CreatedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.DeletedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.GetByIdAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.UpdatedDefaultAdressStateResponse;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface AddressMapper {
    @Mapping(target = "city.id", source = "cityId")
    Address toCustomerAddress(AddressDto addressDto);
    @Mapping(target = "city.id", source = "cityId")
    @Mapping(target = "customer.id",source = "customerId")
    Address toCustomerAddressWithRequest(CreateAddressRequest createAddressRequest);
    //TODO:ASK city.name to cityName
    @Mapping(target = "cityName",source = "city.name")
    CreatedAddressResponse toCreateAddressResponse(Address address);
    GetByIdAddressResponse toGetByIdAddressResponse(Address address);
    @Mapping(target = "cityName",source = "city.name")
    UpdatedDefaultAdressStateResponse toUpdatedDefaultAdressStateResponse(Address address);
    @Mapping(target = "cityName",source = "city.name")
    DeletedAddressResponse toDeletedAddressResponse(Address address);
}
