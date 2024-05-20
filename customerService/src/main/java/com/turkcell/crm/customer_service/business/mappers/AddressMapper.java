package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.UpdateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.*;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import org.mapstruct.*;

@Mapper(config = MapstructService.class)
public interface AddressMapper {
    @Mapping(target = "city.id", source = "cityId")
    Address toCustomerAddress(AddressDto addressDto);

    @Mapping(target = "city.id", source = "cityId")
    @Mapping(target = "customer.id", source = "customerId")
    Address toCustomerAddressWithRequest(CreateAddressRequest createAddressRequest);

    @Mapping(target = "cityId", source = "city.id")
    CreatedAddressResponse toCreateAddressResponse(Address address);

    @Mapping(target = "cityId", source = "city.id")
    GetByIdAddressResponse toGetByIdAddressResponse(Address address);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromRequest(UpdateAddressRequest updateAddressRequest, @MappingTarget Address address);

    @Mapping(target ="cityId",source = "city.id")
    UpdatedAddressResponse toUpdateAddressResponse(Address address);

    @Mapping(target = "cityId", source = "city.id")
    ChangedDefaultAddressResponse toChangedDefaultAddressResponse(Address address);

    @Mapping(target = "cityId", source = "city.id")
    DeletedAddressResponse toDeletedAddressResponse(Address address);
}
