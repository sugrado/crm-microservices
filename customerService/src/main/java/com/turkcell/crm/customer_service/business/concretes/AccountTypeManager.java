package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.customer_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.customer_service.business.mappers.AccountTypeMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.customer_service.entities.concretes.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeManager implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    @Override
    public CreatedAccountTypeResponse add(CreateAccountTypeRequest createAccountTypeRequest) {
        AccountType accountType=this.accountTypeMapper.toAccountType(createAccountTypeRequest);
        AccountType createdAccountType=this.accountTypeRepository.save(accountType);
        CreatedAccountTypeResponse createdAccountTypeResponse=this.accountTypeMapper.toCreatedAccountTypeResponse(createdAccountType);
        return createdAccountTypeResponse;
    }

    @Override
    public AccountType getById(int id) {
        return this.accountTypeRepository.findById(id).get();
    }

    @Override
    public List<GetAllAccountTypeResponse> getAll() {
        return this.accountTypeMapper.toGetAllAccountTypeResponse(this.accountTypeRepository.findAll());
    }
}
