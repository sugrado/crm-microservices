package com.turkcell.crm.account_service.business.mappers;

import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.account_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface AccountTypeMapper {
    AccountType toAccountType(CreateAccountTypeRequest createAccountTypeRequest);

    CreatedAccountTypeResponse toCreatedAccountTypeResponse(AccountType accountType);

    GetByIdAccountTypeResponse toGetByIdAccountTypeResponse(AccountType accountType);
}