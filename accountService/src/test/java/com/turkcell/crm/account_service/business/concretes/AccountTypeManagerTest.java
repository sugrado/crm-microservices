package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.DeletedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.account_service.business.mappers.AccountTypeMapper;
import com.turkcell.crm.account_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.account_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountTypeManagerTest {

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @Mock
    private AccountTypeMapper accountTypeMapper;

    @Mock
    private AccountTypeBusinessRules accountTypeBusinessRules;

    @InjectMocks
    private AccountTypeManager accountTypeManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void add_ShouldAddAccountType() {
        // Prepare
        CreateAccountTypeRequest request = new CreateAccountTypeRequest(
                "Test"
        );
        CreatedAccountTypeResponse expectedResponse = new CreatedAccountTypeResponse(
                1,
                LocalDateTime.now(),
                "Test"
        );

        AccountType accountType = new AccountType();
        when(accountTypeMapper.toAccountType(request)).thenReturn(accountType);
        when(accountTypeRepository.save(accountType)).thenReturn(accountType);
        when(accountTypeMapper.toCreatedAccountTypeResponse(accountType)).thenReturn(expectedResponse);

        // Execute
        CreatedAccountTypeResponse response = accountTypeManager.add(request);

        // Verify
        verify(accountTypeBusinessRules).accountTypeNameCannotBeDuplicatedWhenInserted(request.name());
        assertEquals(expectedResponse, response);
    }

    @Test
    void getById_ShouldReturnAccountTypeForSpecificId() {
        // Prepare
        int accountTypeId = 1;
        Optional<AccountType> optionalAccountType = Optional.of(new AccountType());
        GetByIdAccountTypeResponse expectedResponse = new GetByIdAccountTypeResponse(
                1,
                "Test"
        );

        when(accountTypeRepository.findById(accountTypeId)).thenReturn(optionalAccountType);
        when(accountTypeMapper.toGetByIdAccountTypeResponse(optionalAccountType.get())).thenReturn(expectedResponse);

        // Execute
        GetByIdAccountTypeResponse response = accountTypeManager.getById(accountTypeId);

        // Verify
        verify(accountTypeBusinessRules).accountTypeShouldBeExist(optionalAccountType);
        verify(accountTypeBusinessRules).accountTypeShouldBeNotDeleted(optionalAccountType);
        assertEquals(expectedResponse, response);
    }

    @Test
    void delete_ShouldDeleteAccountTypeForSpecificId() {
        // Prepare
        int accountTypeId = 1;
        Optional<AccountType> optionalAccountType = Optional.of(new AccountType());
        DeletedAccountTypeResponse expectedResponse = new DeletedAccountTypeResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Test"
        );

        when(accountTypeRepository.findById(accountTypeId)).thenReturn(optionalAccountType);
        when(accountTypeMapper.toDeletedAccountTypeResponse(any())).thenReturn(expectedResponse);

        // Execute
        DeletedAccountTypeResponse response = accountTypeManager.delete(accountTypeId);

        // Verify
        verify(accountTypeBusinessRules).accountTypeShouldBeExist(optionalAccountType);
        verify(accountTypeBusinessRules).accountTypeShouldBeNotDeleted(optionalAccountType);
        assertEquals(expectedResponse, response);
    }

    @Test
    void getAll_ShouldReturnAllAccountTypes() {
        // Prepare

        AccountType accountType1 = new AccountType();
        accountType1.setId(1);
        accountType1.setName("Test");
        AccountType accountType2 = new AccountType();
        accountType2.setId(2);
        accountType2.setName("Test2");

        GetAllAccountTypeResponse getAllAccountTypeResponse = new GetAllAccountTypeResponse(1, LocalDateTime.now(), "Test");
        GetAllAccountTypeResponse getAllAccountTypeResponse1 = new GetAllAccountTypeResponse(1, LocalDateTime.now(), "Test2");

        List<AccountType> accountTypeList = Arrays.asList(accountType1, accountType2);
        List<GetAllAccountTypeResponse> getAllAccountTypeResponseList = Arrays.asList(getAllAccountTypeResponse,getAllAccountTypeResponse1);
        when(accountTypeRepository.findAll()).thenReturn(accountTypeList);
        when(accountTypeMapper.toGetAllAccountTypeResponse(accountTypeList)).thenReturn(getAllAccountTypeResponseList);

        // Execute
        List<GetAllAccountTypeResponse> response = accountTypeManager.getAll();

        // Verify
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(getAllAccountTypeResponse, response.get(0));
        assertEquals(getAllAccountTypeResponse1, response.get(1));

        verify(accountTypeRepository, times(1)).findAll();
        verify(accountTypeMapper, times(1)).toGetAllAccountTypeResponse(accountTypeList);
    }
}
