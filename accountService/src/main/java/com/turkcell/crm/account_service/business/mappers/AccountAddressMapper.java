package com.turkcell.crm.account_service.business.mappers;

import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface AccountAddressMapper {
    AccountAddress toAccountAddress(CreateAccountAddressRequest createAccountAddressRequest);

    @Mapping(target = "accountId", source = "account.id")
    CreatedAccountAddressResponse toCreatedAccountAddressResponse(AccountAddress accountAddress);
}
