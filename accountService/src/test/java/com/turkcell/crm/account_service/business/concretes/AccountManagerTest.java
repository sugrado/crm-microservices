package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.UpdateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.*;
import com.turkcell.crm.account_service.business.mappers.AccountAddressMapper;
import com.turkcell.crm.account_service.business.mappers.AccountMapper;
import com.turkcell.crm.account_service.business.mappers.AccountTypeMapper;
import com.turkcell.crm.account_service.business.rules.AccountAddressBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.data_access.abstracts.AccountAddressRepository;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import com.turkcell.crm.account_service.entities.enums.Status;
import com.turkcell.crm.account_service.kafka.producers.AccountProducer;
import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountResponse;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountManagerTest {

    private AccountRepository accountRepository;
    private AccountTypeRepository accountTypeRepository;
    private AccountManager accountManager;
    private AccountAddressRepository accountAddressRepository;
    private CustomerClient customerClient;
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountTypeRepository = mock(AccountTypeRepository.class);
        accountAddressRepository = mock(AccountAddressRepository.class);

        messageService = mock(MessageService.class);
        customerClient = mock(CustomerClient.class);

        AccountMapper accountMapper = new AccountMapperImpl();
        AccountAddressMapper accountAddressMapper = new AccountAddressMapperImpl();
        AccountTypeMapper accountTypeMapper = new AccountTypeMapperImpl();

        AccountAddressBusinessRules accountAddressBusinessRules = new AccountAddressBusinessRules(accountAddressRepository, accountRepository, customerClient, messageService);
        AccountBusinessRules accountBusinessRules = new AccountBusinessRules(messageService, accountRepository, customerClient);
        AccountTypeBusinessRules accountTypeBusinessRules = new AccountTypeBusinessRules(messageService, accountTypeRepository);

        AccountTypeService accountTypeService = new AccountTypeManager(accountTypeRepository, accountTypeMapper, accountTypeBusinessRules);
        AccountAddressService accountAddressService = new AccountAddressManager(accountAddressRepository, accountAddressMapper, accountAddressBusinessRules, customerClient, accountBusinessRules);
        AccountProducer accountProducer = mock(AccountProducer.class);
        accountManager = new AccountManager(accountRepository, accountMapper, accountAddressService, accountBusinessRules, accountTypeService, accountProducer);

    }

    @Test
    void add_ShouldCreateAccountAndReturnCreatedAccountResponseSuccessful() {
        AccountAddressDto accountAddressDto = new AccountAddressDto(1);
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
        Account createdAccount = new Account(1);
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.save(any())).thenReturn(createdAccount);
        doNothing().when(customerClient).checkIfCustomerExists(anyInt());
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(new AccountType()));
        when(accountAddressRepository.saveAll(anyIterable())).thenReturn(List.of(new AccountAddress(1)));

        // Execute
        CreatedAccountResponse response = accountManager.add(createAccountRequest);

        // Verify
        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void add_ShouldThrowExceptionWhenCustomerNotExist() {
        AccountAddressDto accountAddressDto = new AccountAddressDto(1);
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
        Account createdAccount = new Account(1);
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );


        doThrow(NotFoundException.class).when(customerClient).checkIfCustomerExists(anyInt());


        assertThrows(NotFoundException.class, () -> {
            accountManager.add(createAccountRequest);
        });

    }

    @Test
    void add_ShouldThrowExceptionWhenAccountTypeNotExist() {
        AccountAddressDto accountAddressDto = new AccountAddressDto(1);
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
        Account createdAccount = new Account(1);
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );


        doNothing().when(customerClient).checkIfCustomerExists(anyInt());
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> {
            accountManager.add(createAccountRequest);
        });

    }


    @Test
    void add_ShouldCreateAccountAndAddAddressesWhenAddressesAreNotEmptySuccessful() {
        AccountAddressDto accountAddressDto = new AccountAddressDto(1);
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
        Account createdAccount = new Account(1);
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.save(any())).thenReturn(createdAccount);
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(new AccountType()));

        // Execute
        CreatedAccountResponse response = accountManager.add(createAccountRequest);

        // Verify
        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void add_ShouldCreateAccountAndAddAddressesWhenAddressesAreNotEmptyShouldThrowExceptionWhenCustomerNotExists() {
        AccountAddressDto accountAddressDto = new AccountAddressDto(1);
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
        Account createdAccount = new Account(1);
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.save(any())).thenReturn(createdAccount);
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(new AccountType()));

        doThrow(NotFoundException.class).when(customerClient).checkIfCustomerExists(anyInt());


        assertThrows(NotFoundException.class, () -> {
            accountManager.add(createAccountRequest);
        });

    }

    @Test
    void add_ShouldCreateAccountAndAddAddressesWhenAddressesAreNotEmptyShouldThrowExceptionWhenAccountTypeNotExists() {
        AccountAddressDto accountAddressDto = new AccountAddressDto(1);
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
        Account createdAccount = new Account(1);
        CreatedAccountResponse expectedResponse = new CreatedAccountResponse(
                1,
                LocalDateTime.now(),
                Status.ACTIVE,
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.save(any())).thenReturn(createdAccount);
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(new AccountType()));

        doNothing().when(customerClient).checkIfCustomerExists(anyInt());
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> {
            accountManager.add(createAccountRequest);
        });
    }


    @Test
    void getAll_ShouldReturnAllAccounts() {
        // Prepare
        Account account = new Account();
        account.setId(1);
        Account account1 = new Account();
        account1.setId(2);
        List<Account> accountList = Arrays.asList(account, account1);
        when(accountRepository.findAll()).thenReturn(accountList);

        GetAllAccountsResponse getAllAccountsResponse = new GetAllAccountsResponse(1, LocalDateTime.now(), LocalDateTime.now(), Status.ACTIVE, "Test", "123456", 1, 1);
        GetAllAccountsResponse getAllAccountsResponse1 = new GetAllAccountsResponse(2, LocalDateTime.now(), LocalDateTime.now(), Status.ACTIVE, "Test", "123456", 2, 2);
        List<GetAllAccountsResponse> getAllAccountsResponseList = Arrays.asList(getAllAccountsResponse, getAllAccountsResponse1);


        // Execute
        List<GetAllAccountsResponse> response = accountManager.getAll();

        // Verify
        assertNotNull(response);
        assertEquals(getAllAccountsResponseList.size(), response.size());
        assertEquals(getAllAccountsResponseList.get(0).id(), response.get(0).id());
        assertEquals(getAllAccountsResponseList.get(1).id(), response.get(1).id());
    }

    @Test
    void getById_ShouldReturnAccountForSpecificId() {
        // Prepare
        int accountId = 1;
        GetByIdAccountResponse expectedResponse = new GetByIdAccountResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.ACTIVE.toString(),
                "Test",
                "123456",
                1,
                1
        );

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(new Account(1)));

        // Execute
        GetByIdAccountResponse response = accountManager.getById(accountId);

        // Verify
        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void getById_ShouldThrowExceptionWhenAccountIsDeleted() {
        // Prepare
        GetByIdAccountResponse expectedResponse = new GetByIdAccountResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.ACTIVE.toString(),
                "Test",
                "123456",
                1,
                1
        );
        Account deletedAccount = new Account(1);
        deletedAccount.setDeletedDate(LocalDateTime.now());

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(deletedAccount));

        // Verify
        assertThrows(BusinessException.class, () -> {
            accountManager.getById(anyInt());
        });
    }

    @Test
    void getById_ShouldThrowExceptionWhenAccountNotExits() {
        // Prepare
        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Verify
        assertThrows(NotFoundException.class, () -> {
            accountManager.getById(anyInt());
        });
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

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(new Account()));
        when(accountTypeRepository.findById(anyInt())).thenReturn(Optional.of(new AccountType()));
        when(accountRepository.save(any(Account.class))).thenReturn(new Account(1));

        // Execute
        UpdatedAccountResponse response = accountManager.update(accountId, updateRequest);

        // Verify
        verify(accountRepository).save(any(Account.class));
        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void update_ShouldThrowExceptionWhenAccountNotExist() {

        // Prepare
        int accountId = 1;
        UpdateAccountRequest updateRequest = new UpdateAccountRequest(
                Status.ACTIVE,
                "Test",
                "123456",
                1
        );

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

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            accountManager.update(anyInt(), updateRequest);
        });
    }

    @Test
    void update_ShouldThrowExceptionWhenAccountIsDeleted() {

        // Prepare
        UpdateAccountRequest updateRequest = new UpdateAccountRequest(
                Status.ACTIVE,
                "Test",
                "123456",
                1
        );

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
        Account account = new Account();
        account.setDeletedDate(LocalDateTime.now());

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));

        assertThrows(BusinessException.class, () -> {
            accountManager.update(anyInt(), updateRequest);
        });
    }

    @Test
    void delete_ShouldDeleteAccountForSpecificId() {
        // Prepare
        int accountId = 1;
        Optional<Account> optionalAccount = Optional.of(new Account(1));
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


        // Execute
        DeleteAccountResponse response = accountManager.delete(accountId);

        // Verify
        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void delete_ShouldThrowExceptionWhenAccountNotExist() {
        // Prepare
        int accountId = 1;
        Optional<Account> optionalAccount = Optional.of(new Account(1));
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

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            accountManager.delete(anyInt());
        });
    }

    @Test
    void delete_ShouldThrowExceptionWhenAccountIsDeleted() {
        // Prepare
        int accountId = 1;
        Optional<Account> optionalAccount = Optional.of(new Account(1));
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

        Account account = new Account();
        account.setDeletedDate(LocalDateTime.now());

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));

        assertThrows(BusinessException.class, () -> {
            accountManager.delete(anyInt());
        });
    }

    @Test
    void getAllByCustomerId_ShouldReturnAllAccountsForSpecificCustomerId() {
        // Prepare
        int customerId = 1;
        Account account = new Account();
        account.setId(1);
        Account account1 = new Account();
        account1.setId(2);
        List<Account> accountList = Arrays.asList(account, account1);
        when(accountRepository.findAllByCustomerId(customerId)).thenReturn(accountList);

        GetAllByCustomerIdResponse getAllByCustomerIdResponse = new GetAllByCustomerIdResponse(1, Status.ACTIVE, "Test", "123456", 1);
        GetAllByCustomerIdResponse getAllByCustomerIdResponse1 = new GetAllByCustomerIdResponse(2, Status.ACTIVE, "Test", "123456", 2);
        List<GetAllByCustomerIdResponse> getAllByCustomerIdResponseList = Arrays.asList(getAllByCustomerIdResponse, getAllByCustomerIdResponse1);

        doNothing().when(customerClient).checkIfCustomerExists(anyInt());

        // Execute
        List<GetAllByCustomerIdResponse> response = accountManager.getAllByCustomerId(customerId);

        // Verify

        assertEquals(getAllByCustomerIdResponseList.size(), response.size());
        assertEquals(getAllByCustomerIdResponseList.get(0).id(), response.get(0).id());
        assertEquals(getAllByCustomerIdResponseList.get(1).id(), response.get(1).id());
    }

    @Test
    void getAllByCustomerId_ShouldThrowExceptionWhenCustomerNotExistForSpecificCustomerId() {
        // Prepare
        int customerId = 1;
        Account account = new Account();
        account.setId(1);
        Account account1 = new Account();
        account1.setId(2);
        List<Account> accountList = Arrays.asList(account, account1);
        when(accountRepository.findAllByCustomerId(customerId)).thenReturn(accountList);

        GetAllByCustomerIdResponse getAllByCustomerIdResponse = new GetAllByCustomerIdResponse(1, Status.ACTIVE, "Test", "123456", 1);
        GetAllByCustomerIdResponse getAllByCustomerIdResponse1 = new GetAllByCustomerIdResponse(2, Status.ACTIVE, "Test", "123456", 2);
        List<GetAllByCustomerIdResponse> getAllByCustomerIdResponseList = Arrays.asList(getAllByCustomerIdResponse, getAllByCustomerIdResponse1);

        doThrow(NotFoundException.class).when(customerClient).checkIfCustomerExists(anyInt());

        assertThrows(NotFoundException.class, () -> {
            accountManager.getAllByCustomerId(anyInt());
        });
    }
}
