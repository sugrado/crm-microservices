package com.turkcell.crm.search_service.kafka.consumers;

import com.turkcell.crm.common.shared.kafka.events.accounts.AccountCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.accounts.AccountDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.accounts.AccountUpdatedEvent;
import com.turkcell.crm.common.shared.kafka.events.catalogs.ProductCreatedEvent;
import com.turkcell.crm.search_service.business.abstracts.AccountSearchService;
import com.turkcell.crm.search_service.business.mappers.AccountMapper;
import com.turkcell.crm.search_service.entities.concretes.Account;
import com.turkcell.crm.search_service.entities.concretes.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountConsumer {
    private final AccountMapper accountMapper;
    private final AccountSearchService accountSearchService;

    @KafkaListener(topics = "account-created", groupId = "search.group")
    public void consume(AccountCreatedEvent accountCreatedEvent) {
        Account account = this.accountMapper.toAccount(accountCreatedEvent);
        this.accountSearchService.add(account);
    }

    @KafkaListener(topics = "account-updated", groupId = "search.group")
    public void consume(AccountUpdatedEvent accountUpdatedEvent) {
        Account account = this.accountMapper.toAccount(accountUpdatedEvent);
        this.accountSearchService.update(account);
    }

    @KafkaListener(topics = "account-updated", groupId = "search.group")
    public void consume(AccountDeletedEvent accountDeletedEvent) {
        this.accountSearchService.delete(accountDeletedEvent.getId());
    }


}
