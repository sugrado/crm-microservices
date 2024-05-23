package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.abstracts.AccountService;
import com.turkcell.crm.account_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.UpdateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.*;
import com.turkcell.crm.account_service.business.mappers.AccountMapper;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountManagerTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private AccountAddressService accountAddressService;

    @Mock
    private AccountBusinessRules accountBusinessRules;

    @Mock
    private AccountTypeService accountTypeService;

    @InjectMocks
    private AccountManager accountManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void add_ShouldAddAccount() {
        List<AccountAddressDto> accountAddressDtoList = new ArrayList<AccountAddressDto>();
        // Prepare
        CreateAccountRequest request = new CreateAccountRequest(
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1,
                accountAddressDtoList
        );
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        Account account = new Account();
        when(accountMapper.toAccount(request)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountMapper.toCreatedAccountResponse(account)).thenReturn(expectedResponse);

        // Execute
        CreatedAccountResponse response = accountManager.add(request);

        // Verify
        verify(accountBusinessRules).customerShouldBeExists(request.customerId());
        verify(accountTypeService).getById(request.typeId());
        verify(accountAddressService).add(request.accountAddresses(), account);
        assertEquals(expectedResponse, response);
    }

    @Test
    void getAll_ShouldReturnAllAccounts() {
        // Prepare
        List<Account> accountList = List.of(new Account(), new Account());
        when(accountRepository.findAll()).thenReturn(accountList);

        // Execute
        List<GetAllAccountsResponse> response = accountManager.getAll();

        // Verify
        assertEquals(accountList.size(), response.size());
    }

    @Test
    void getById_ShouldReturnAccountForSpecificId() {
        // Prepare
        int accountId = 1;
        Optional<Account> optionalAccount = Optional.of(new Account());
        GetByIdAccountResponse expectedResponse = new GetByIdAccountResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.findById(accountId)).thenReturn(optionalAccount);
        when(accountMapper.toGetByIdAccountResponse(optionalAccount.get())).thenReturn(expectedResponse);

        // Execute
        GetByIdAccountResponse response = accountManager.getById(accountId);

        // Verify
        verify(accountBusinessRules).accountShouldBeExist(optionalAccount);
        verify(accountBusinessRules).accountShouldBeNotDeleted(optionalAccount);
        assertEquals(expectedResponse, response);
    }

    @Test
    void update_ShouldUpdateAccountForSpecificId() {
        MessageService messageService = mock(MessageService.class);
        AccountTypeRepository accountTypeRepository = mock(AccountTypeRepository.class);
        AccountTypeBusinessRules accountTypeBusinessRules = new AccountTypeBusinessRules(
                messageService,
                accountTypeRepository);
        // Prepare
        int accountId = 1;
        UpdateAccountRequest updateRequest = new UpdateAccountRequest(
                Status.ACTIVE,
                "Test",
                "123456",
                1
        );
        Optional<Account> optionalAccount = Optional.of(new Account());
        UpdatedAccountResponse expectedResponse = new UpdatedAccountResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.findById(accountId)).thenReturn(optionalAccount);
        when(accountMapper.toGetByIdAccountResponse(optionalAccount.get())).thenReturn(new GetByIdAccountResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        ));
        when(accountTypeService.getById(anyInt()));//.thenReturn(accountTypeBusinessRules); Bu çalışmıyor buna bir bakalım.
        when(accountRepository.save(any(Account.class))).thenReturn(new Account());
        when(accountMapper.toUpdatedAccountResponse(any(Account.class))).thenReturn(expectedResponse);

        // Execute
        UpdatedAccountResponse response = accountManager.update(accountId, updateRequest);

        // Verify
        verify(accountBusinessRules).accountShouldBeExist(optionalAccount);
        verify(accountBusinessRules).accountShouldBeNotDeleted(optionalAccount);
        verify(accountTypeService).getById(anyInt());
        verify(accountRepository).save(any(Account.class));
        assertEquals(expectedResponse, response);
    }

    @Test
    void delete_ShouldDeleteAccountForSpecificId() {
        // Prepare
        int accountId = 1;
        Optional<Account> optionalAccount = Optional.of(new Account());
        DeleteAccountResponse expectedResponse = new DeleteAccountResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.PASSIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.findById(accountId)).thenReturn(optionalAccount);
        when(accountMapper.toDeleteAccountResponse(any(Account.class))).thenReturn(expectedResponse);

        // Execute
        DeleteAccountResponse response = accountManager.delete(accountId);

        // Verify
        verify(accountBusinessRules).accountShouldBeExist(optionalAccount);
        verify(accountBusinessRules).accountShouldBeNotDeleted(optionalAccount);
        assertEquals(expectedResponse, response);
    }

    @Test
    void getAllByCustomerId_ShouldReturnAllAccountsForSpecificCustomerId() {
        // Prepare
        int customerId = 1;
        List<Account> accountList = List.of(new Account(), new Account());
        when(accountRepository.findAllByCustomerId(customerId)).thenReturn(accountList);

        // Execute
        List<GetAllByCustomerIdResponse> response = accountManager.getAllByCustomerId(customerId);

        // Verify
        verify(accountBusinessRules).customerShouldBeExists(customerId);
        assertEquals(accountList.size(), response.size());
    }
}
