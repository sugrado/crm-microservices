package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.accounts.CreatedAccountResponse;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface AccountMapper {
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "type.id", source = "typeId")
    Account toAccount(CreateAccountRequest createAccountRequest);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "typeId", source = "type.id")
    CreatedAccountResponse toCreatedAccountResponse(Account account);
}
