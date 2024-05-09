package com.turkcell.crm.account_service.business.mappers;

import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.CreatedAccountResponse;
import com.turkcell.crm.account_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.account_service.entities.concretes.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface AccountMapper {
    @Mapping(target = "type.id", source = "typeId")
    Account toAccount(CreateAccountRequest createAccountRequest);

    @Mapping(target = "typeId", source = "type.id")
    CreatedAccountResponse toCreatedAccountResponse(Account account);
}
