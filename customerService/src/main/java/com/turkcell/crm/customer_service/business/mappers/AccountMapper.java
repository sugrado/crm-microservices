package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.accounts.CreateAccountResponse;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.Account;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface AccountMapper {
    Account toAccount(CreateAccountRequest createAccountRequest);
    CreateAccountResponse toCreatedAccountResponse(Account account);
}
