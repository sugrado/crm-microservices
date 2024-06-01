package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.UpdateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.*;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import org.mapstruct.*;

@Mapper(config = MapstructService.class)
public interface AddressMapper {
    @Mapping(target = "city.id", source = "cityId")
    @Mapping(target = "district.id", source = "districtId")
    Address toCustomerAddress(AddressDto addressDto);

    @Mapping(target = "city.id", source = "cityId")
    @Mapping(target = "district.id", source = "districtId")
    @Mapping(target = "customer.id", source = "customerId")
    Address toCustomerAddressWithRequest(CreateAddressRequest createAddressRequest);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "districtId", source = "district.id")
    CreatedAddressResponse toCreateAddressResponse(Address address);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "districtId", source = "district.id")
    @Mapping(target = "customerId", source = "customer.id")
    GetByIdAddressResponse toGetByIdAddressResponse(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromRequest(UpdateAddressRequest updateAddressRequest, @MappingTarget Address address);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "districtId", source = "district.id")
    UpdatedAddressResponse toUpdateAddressResponse(Address address);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "districtId", source = "district.id")
    ChangedDefaultAddressResponse toChangedDefaultAddressResponse(Address address);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "districtId", source = "district.id")
    DeletedAddressResponse toDeletedAddressResponse(Address address);
}
