package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.DeletedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.account_service.business.mappers.AccountTypeMapper;
import com.turkcell.crm.account_service.business.mappers.AccountTypeMapperImpl;
import com.turkcell.crm.account_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountTypeManagerTest {


    private AccountTypeRepository accountTypeRepository;
    private AccountTypeManager accountTypeManager;
    private MessageService messageService;


    @BeforeEach
    void setUp() {
        AccountTypeMapper accountTypeMapper = new AccountTypeMapperImpl();

        accountTypeRepository = mock(AccountTypeRepository.class);

        messageService = mock(MessageService.class);

        AccountTypeBusinessRules accountTypeBusinessRules = new AccountTypeBusinessRules(messageService, accountTypeRepository);

        accountTypeManager = new AccountTypeManager(accountTypeRepository, accountTypeMapper, accountTypeBusinessRules);
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
        accountType.setId(1);

        when(accountTypeRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(new AccountType()));
        when(accountTypeRepository.save(any())).thenReturn(accountType);

        // Execute
        CreatedAccountTypeResponse response = accountTypeManager.add(request);

        // Verify

        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void add_ShouldThrowExceptionWhenAccountTypeExistWithSameName() {
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
        accountType.setId(1);

        when(accountTypeRepository.findByName(anyString())).thenReturn(Optional.of(new AccountType()));

        // Verify
        assertThrows(BusinessException.class, () -> {
            accountTypeManager.add(request);
        });
    }

    @Test
    void getById_ShouldReturnAccountTypeForSpecificId() {
        // Prepare

        AccountType accountType = new AccountType();
        accountType.setId(1);

        GetByIdAccountTypeResponse expectedResponse = new GetByIdAccountTypeResponse(
                1,
                "Test"
        );

        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(accountType));

        // Execute
        GetByIdAccountTypeResponse response = accountTypeManager.getById(anyInt());

        // Verify

        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void getById_ShouldThrowExceptionWhenNotExistingAccountType() {
        // Prepare

        AccountType accountType = new AccountType();
        accountType.setId(1);

        GetByIdAccountTypeResponse expectedResponse = new GetByIdAccountTypeResponse(
                1,
                "Test"
        );

        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

        //verify

        assertThrows(NotFoundException.class, () -> {
            accountTypeManager.getById(anyInt());
        });
    }

    @Test
    void getById_ShouldThrowExceptionAccountTypeIsDeleted() {
        // Prepare

        AccountType accountType = new AccountType();
        accountType.setId(1);
        accountType.setDeletedDate(LocalDateTime.now());

        GetByIdAccountTypeResponse expectedResponse = new GetByIdAccountTypeResponse(
                1,
                "Test"
        );

        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(accountType));

        //verify

        assertThrows(BusinessException.class, () -> {
            accountTypeManager.delete(anyInt());
        });
    }

    @Test
    void delete_ShouldDeleteAccountTypeForSpecificId() {
        // Prepare


        DeletedAccountTypeResponse expectedResponse = new DeletedAccountTypeResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Test"
        );
        AccountType deletedAccountType = new AccountType();
        deletedAccountType.setId(1);

        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Verify
        assertThrows(NotFoundException.class, () -> {
            accountTypeManager.delete(anyInt());
        });

    }

    @Test
    void delete_ShouldThrowExceptionWhenNotExistingAccountType() {
        // Prepare


        DeletedAccountTypeResponse expectedResponse = new DeletedAccountTypeResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Test"
        );
        AccountType deletedAccountType = new AccountType();
        deletedAccountType.setId(1);

        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(new AccountType()));
        when(accountTypeRepository.save(any())).thenReturn(deletedAccountType);

        // Execute
        DeletedAccountTypeResponse response = accountTypeManager.delete(anyInt());

        // Verify
        assertEquals(expectedResponse.id(), response.id());
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
        GetAllAccountTypeResponse getAllAccountTypeResponse1 = new GetAllAccountTypeResponse(2, LocalDateTime.now(), "Test2");

        List<AccountType> accountTypeList = Arrays.asList(accountType1, accountType2);
        List<GetAllAccountTypeResponse> getAllAccountTypeResponseList = Arrays.asList(getAllAccountTypeResponse, getAllAccountTypeResponse1);
        when(accountTypeRepository.findAll()).thenReturn(accountTypeList);

        // Execute
        List<GetAllAccountTypeResponse> response = accountTypeManager.getAll();

        // Verify

        assertEquals(getAllAccountTypeResponseList.size(), response.size());
        assertEquals(getAllAccountTypeResponseList.get(0).id(), response.get(0).id());
        assertEquals(getAllAccountTypeResponseList.get(1).id(), response.get(1).id());


    }
}
