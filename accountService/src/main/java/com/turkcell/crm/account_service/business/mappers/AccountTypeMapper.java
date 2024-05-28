package com.turkcell.crm.account_service.business.mappers;

import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.DeletedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface AccountTypeMapper {
    AccountType toAccountType(CreateAccountTypeRequest createAccountTypeRequest);

    CreatedAccountTypeResponse toCreatedAccountTypeResponse(AccountType accountType);

    GetByIdAccountTypeResponse toGetByIdAccountTypeResponse(AccountType accountType);

    DeletedAccountTypeResponse toDeletedAccountTypeResponse(AccountType accountType);

    GetAllAccountTypeResponse toGetAllAccountTypeResponse(AccountType accountType);

    List<GetAllAccountTypeResponse> toGetAllAccountTypeResponse(List<AccountType> accountTypeList);
}
