package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.DeletedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.account_service.business.mappers.AccountTypeMapper;
import com.turkcell.crm.account_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.account_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTypeManager implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeBusinessRules accountTypeBusinessRules;

    @Override
    public CreatedAccountTypeResponse add(CreateAccountTypeRequest createAccountTypeRequest) {
        this.accountTypeBusinessRules.accountTypeNameCannotBeDuplicatedWhenInserted(createAccountTypeRequest.name());

        AccountType accountType = this.accountTypeMapper.toAccountType(createAccountTypeRequest);
        AccountType createdAccountType = this.accountTypeRepository.save(accountType);

        return this.accountTypeMapper.toCreatedAccountTypeResponse(createdAccountType);
    }

    @Override
    public GetByIdAccountTypeResponse getById(int id) {
        Optional<AccountType> optionalAccountType = this.accountTypeRepository.findById(id);

        this.accountTypeBusinessRules.accountTypeShouldBeExist(optionalAccountType);
        this.accountTypeBusinessRules.accountTypeShouldBeNotDeleted(optionalAccountType);

        AccountType accountType = optionalAccountType.get();

        return this.accountTypeMapper.toGetByIdAccountTypeResponse(accountType);
    }

    @Override
    public DeletedAccountTypeResponse delete(int id) {
        Optional<AccountType> optionalAccountType = this.accountTypeRepository.findById(id);

        this.accountTypeBusinessRules.accountTypeShouldBeExist(optionalAccountType);
        this.accountTypeBusinessRules.accountTypeShouldBeNotDeleted(optionalAccountType);

        AccountType deletedAccountType = optionalAccountType.get();
        deletedAccountType.setDeletedDate(LocalDateTime.now());

        this.accountTypeRepository.save(deletedAccountType);

        return this.accountTypeMapper.toDeletedAccountTypeResponse(deletedAccountType);
    }

    @Override
    public List<GetAllAccountTypeResponse> getAll() {
        List<AccountType> accountTypeList = this.accountTypeRepository.findAll();

        return this.accountTypeMapper.toGetAllAccountTypeResponse(accountTypeList);
    }
}
