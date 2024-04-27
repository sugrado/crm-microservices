package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.AccountAddress;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface AccountAddressMapper {
    AccountAddress toAccountAddress(AccountAddressDto accountAddressDto);
}
