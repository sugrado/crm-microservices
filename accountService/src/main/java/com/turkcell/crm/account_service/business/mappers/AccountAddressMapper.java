package com.turkcell.crm.account_service.business.mappers;

import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountAddressResponse;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface AccountAddressMapper {
    AccountAddress toAccountAddress(CreateAccountAddressRequest createAccountAddressRequest);

    AccountAddress toAccountAddress(AccountAddressDto accountAddressDto);

    @Mapping(target = "accountId", source = "account.id")
    CreatedAccountAddressResponse toCreatedAccountAddressResponse(AccountAddress accountAddress);

    @Mapping(target = "accountId", source = "account.id")
    DeletedAcountAddressResponse toDeletedAcountAddressResponse(AccountAddress accountAddress);

    @Mapping(target = "accountId", source = "account.id")
    GetAllByAccountIdResponse toGetAllByAccountIdResponse(AccountAddress accountAddress);

    List<GetAllByAccountIdResponse> toGetAllByAccountIdResponse(List<AccountAddress> accountAddressList);

    GetByIdAccountAddressResponse toGetByIdAccountAddressResponse(AccountAddress accountAddress);
}
