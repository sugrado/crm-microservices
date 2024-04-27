package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.AccountAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface AccountAddressMapper {
    @Mapping(target = "address.id", source = "addressId")
    AccountAddress toAccountAddress(AccountAddressDto accountAddressDto);

    @Mapping(target = "address.id", source = "addressId")
    AccountAddress toAccountAddress(CreateAccountAddressRequest createAccountAddressRequest);

    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "accountId", source = "account.id")
    CreatedAccountAddressResponse toCreatedAccountAddressResponse(AccountAddress accountAddress);
}
