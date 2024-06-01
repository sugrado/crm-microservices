package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.accounts.AccountCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.accounts.AccountUpdatedEvent;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.search_service.entities.concretes.Account;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface AccountMapper {
  Account toAccount(AccountCreatedEvent accountCreatedEvent);
  Account toAccount(AccountUpdatedEvent accountUpdatedEvent);
}
