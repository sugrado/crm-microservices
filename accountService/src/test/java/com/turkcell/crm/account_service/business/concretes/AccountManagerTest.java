package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.UpdateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.*;
import com.turkcell.crm.account_service.business.mappers.AccountMapper;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import com.turkcell.crm.account_service.entities.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    void add_ShouldCreateAccountAndReturnCreatedAccountResponse() {
        AccountAddressDto accountAddressDto=new AccountAddressDto(1);
        List<AccountAddressDto> accountAddressDtoList = Arrays.asList(accountAddressDto);
        // Prepare
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1,
                accountAddressDtoList
        );

        Account account = new Account();
        Account createdAccount = new Account();
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountMapper.toAccount(createAccountRequest)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(createdAccount);
        when(accountMapper.toCreatedAccountResponse(createdAccount)).thenReturn(expectedResponse);

        // Execute
        CreatedAccountResponse response = accountManager.add(createAccountRequest);

        // Verify
        verify(accountBusinessRules).customerShouldBeExists(createAccountRequest.customerId());
        verify(accountTypeService).getById(createAccountRequest.typeId());
        verify(accountRepository).save(account);
        verify(accountMapper).toCreatedAccountResponse(createdAccount);
        assertEquals(expectedResponse, response);
    }

    @Test
    void add_ShouldCreateAccountAndAddAddressesWhenAddressesAreNotEmpty() {
        AccountAddressDto accountAddressDto=new AccountAddressDto(1);
        List<AccountAddressDto> accountAddressDtoList = Arrays.asList(accountAddressDto);
        // Prepare
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(
                 Status.ACTIVE,
                "Test",
                "123456",
                1,
                1,
                accountAddressDtoList
        );

        Account account = new Account();
        Account createdAccount = new Account();
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountMapper.toAccount(createAccountRequest)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(createdAccount);
        when(accountMapper.toCreatedAccountResponse(createdAccount)).thenReturn(expectedResponse);

        // Execute
        CreatedAccountResponse response = accountManager.add(createAccountRequest);

        // Verify
        verify(accountBusinessRules).customerShouldBeExists(createAccountRequest.customerId());
        verify(accountTypeService).getById(createAccountRequest.typeId());
        verify(accountRepository).save(account);
        verify(accountMapper).toCreatedAccountResponse(createdAccount);
        verify(accountAddressService).add(createAccountRequest.accountAddresses(), createdAccount);
        assertEquals(expectedResponse, response);
    }


    @Test
    void getAll_ShouldReturnAllAccounts() {
        // Prepare
        Account account = new Account();
        account.setId(1);
        Account account1 = new Account();
        account1.setId(2);
        List<Account> accountList = Arrays.asList(account,account1);
        when(accountRepository.findAll()).thenReturn(accountList);

        GetAllAccountsResponse getAllAccountsResponse = new GetAllAccountsResponse(1, LocalDateTime.now(), LocalDateTime.now(),Status.ACTIVE, "Test","123456",1,1);
        GetAllAccountsResponse getAllAccountsResponse1 = new GetAllAccountsResponse(2, LocalDateTime.now(), LocalDateTime.now(),Status.ACTIVE, "Test","123456",2,2);
        List<GetAllAccountsResponse> getAllAccountsResponseList = Arrays.asList(getAllAccountsResponse,getAllAccountsResponse1);

        when(accountMapper.toGetAllAccountsResponse(accountList)).thenReturn(getAllAccountsResponseList);

        // Execute
        List<GetAllAccountsResponse> response = accountManager.getAll();

        // Verify
        assertNotNull(response);
        assertEquals(accountList.size(), response.size());
        assertEquals(accountList.get(0).getId(),response.get(0).id());
        assertEquals(accountList.get(1).getId(),response.get(1).id());
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

        // Prepare
        int accountId = 1;
        UpdateAccountRequest updateRequest = new UpdateAccountRequest(
                Status.ACTIVE,
                "Test",
                "123456",
                1
        );
        AccountType accountType = new AccountType();
        accountType.setId(1);
        Account existingAccount = new Account();
        existingAccount.setId(accountId);
        existingAccount.setType(accountType);

        Optional<Account> optionalAccount = Optional.of(existingAccount);
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
        when(accountTypeService.getById(anyInt())).thenReturn(new GetByIdAccountTypeResponse(
                1,
                "Test"));
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
        Account account = new Account();
        account.setId(1);
        Account account1 = new Account();
        account1.setId(2);
        List<Account> accountList = Arrays.asList(account,account1);
        when(accountRepository.findAllByCustomerId(customerId)).thenReturn(accountList);

        GetAllByCustomerIdResponse getAllByCustomerIdResponse = new GetAllByCustomerIdResponse(1,Status.ACTIVE, "Test","123456",1);
        GetAllByCustomerIdResponse getAllByCustomerIdResponse1 = new GetAllByCustomerIdResponse(2,Status.ACTIVE, "Test","123456",2);
        List<GetAllByCustomerIdResponse> getAllByCustomerIdResponseList = Arrays.asList(getAllByCustomerIdResponse,getAllByCustomerIdResponse1);

        when(accountMapper.toGetAllByCustomerIdResponse(accountList)).thenReturn(getAllByCustomerIdResponseList);

        // Execute
        List<GetAllByCustomerIdResponse> response = accountManager.getAllByCustomerId(customerId);

        // Verify
        verify(accountBusinessRules).customerShouldBeExists(customerId);

        assertNotNull(response);
        assertEquals(accountList.size(), response.size());
        assertEquals(accountList.get(0).getId(),response.get(0).id());
        assertEquals(accountList.get(1).getId() ,response.get(1).id());
    }
}
