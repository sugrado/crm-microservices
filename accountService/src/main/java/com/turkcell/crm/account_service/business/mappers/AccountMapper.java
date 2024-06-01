package com.turkcell.crm.account_service.business.mappers;

import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.UpdateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.*;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountResponse;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface AccountMapper {
    @Mapping(target = "type.id", source = "typeId")
    Account toAccount(CreateAccountRequest createAccountRequest);

    @Mapping(target = "typeId", source = "type.id")
    CreatedAccountResponse toCreatedAccountResponse(Account account);

    @Mapping(target = "typeId", source = "type.id")
    GetByIdAccountResponse toGetByIdAccountResponse(Account account);

    @Mapping(target = "typeId", source = "type.id")
    GetAllAccountsResponse toGetAllAccountsResponse(Account account);

    List<GetAllAccountsResponse> toGetAllAccountsResponse(List<Account> accounts);

    @Mapping(target = "typeId", source = "type.id")
    GetAllByCustomerIdResponse toGetAllByCustomerIdResponse(Account account);

    List<GetAllByCustomerIdResponse> toGetAllByCustomerIdResponse(List<Account> accounts);

    @Mapping(target = "typeId", source = "type.id")
    UpdatedAccountResponse toUpdatedAccountResponse(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "type.id", source = "typeId")
    void updateAccountFromRequest(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

    @Mapping(target = "typeId", source = "type.id")
    DeleteAccountResponse toDeleteAccountResponse(Account account);
}
