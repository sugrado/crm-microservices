package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.customer_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.customer_service.business.mappers.AccountTypeMapper;
import com.turkcell.crm.customer_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.customer_service.entities.concretes.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTypeManager implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeBusinessRules accountTypeBusinessRules;

    @Override
    public CreatedAccountTypeResponse add(CreateAccountTypeRequest createAccountTypeRequest) {
        AccountType accountType = this.accountTypeMapper.toAccountType(createAccountTypeRequest);
        AccountType createdAccountType = this.accountTypeRepository.save(accountType);
        return this.accountTypeMapper.toCreatedAccountTypeResponse(createdAccountType);
    }

    @Override
    public GetByIdAccountTypeResponse getById(int id) {
        Optional<AccountType> optionalAccountType = this.accountTypeRepository.findById(id);
        accountTypeBusinessRules.accountTypeShouldBeExist(optionalAccountType);
        AccountType accountType = optionalAccountType.get();
        return this.accountTypeMapper.toGetByIdAccountTypeResponse(accountType);
    }
}
