package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.account_service.business.mappers.AccountAddressMapper;
import com.turkcell.crm.account_service.business.mappers.AccountAddressMapperImpl;
import com.turkcell.crm.account_service.business.rules.AccountAddressBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.data_access.abstracts.AccountAddressRepository;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import com.turkcell.crm.common.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.exceptions.types.BusinessException;
import com.turkcell.crm.common.exceptions.types.NotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountAddressManagerTest {

    private AccountAddressRepository accountAddressRepository;
    private CustomerClient customerClient;
    private AccountAddressManager accountAddressManager;
    private MessageService messageService;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountAddressRepository = mock(AccountAddressRepository.class);
        customerClient = mock(CustomerClient.class);
        AccountAddressMapper accountAddressMapper = new AccountAddressMapperImpl();
        messageService = mock(MessageService.class);
        accountRepository = mock(AccountRepository.class);
        AccountAddressBusinessRules accountAddressBusinessRules = new AccountAddressBusinessRules(accountAddressRepository, accountRepository, customerClient, messageService);
        AccountBusinessRules accountBusinessRules = new AccountBusinessRules(messageService, accountRepository, customerClient);

        accountAddressManager = new AccountAddressManager(accountAddressRepository, accountAddressMapper, accountAddressBusinessRules, customerClient, accountBusinessRules);
    }

    @Test
    void add_ShouldAddAccountAddressSuccessful() {
        // Prepare
        int accountId = 1;
        CreateAccountAddressRequest request = new CreateAccountAddressRequest(1);


        AccountAddress accountAddress = new AccountAddress(accountId);
        accountAddress.setAddressId(1);
        accountAddress.setAccount(new Account(1));
        CreatedAccountAddressResponse expectedResponse = new CreatedAccountAddressResponse(
                1,
                1,
                accountId
        );

        when(accountAddressRepository.save(any())).thenReturn(accountAddress);
        doNothing().when(customerClient).checkIfAddressExists(anyInt());
        when(accountAddressRepository.existsByAccountIdAndAddressId(anyInt(), anyInt())).thenReturn(false);
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account(1)));
        doNothing().when(customerClient).checkAddressAndCustomerMatch(any());


        // Execute
        CreatedAccountAddressResponse response = accountAddressManager.add(accountId, request);

        // Verify
        assertEquals(expectedResponse, response);
    }

    @Test
    void add_ShouldThrowExceptionWhenAddressNotExists() {
        // Prepare
        int accountId = 1;
        CreateAccountAddressRequest request = new CreateAccountAddressRequest(1);


        AccountAddress accountAddress = new AccountAddress(accountId);
        accountAddress.setAddressId(1);
        accountAddress.setAccount(new Account(1));
        CreatedAccountAddressResponse expectedResponse = new CreatedAccountAddressResponse(
                1,
                1,
                accountId
        );

        doThrow(NotFoundException.class).when(customerClient).checkIfAddressExists(anyInt());

        assertThrows(NotFoundException.class, () -> {
            accountAddressManager.add(accountId, request);
        });
    }
    @Test
    void add_ShouldThrowExceptionWhenAccountNotExists() {
        // Prepare
        int accountId = 1;
        CreateAccountAddressRequest request = new CreateAccountAddressRequest(1);


        AccountAddress accountAddress = new AccountAddress(accountId);
        accountAddress.setAddressId(1);
        accountAddress.setAccount(new Account(1));
        CreatedAccountAddressResponse expectedResponse = new CreatedAccountAddressResponse(
                1,
                1,
                accountId
        );

        doNothing().when(customerClient).checkIfAddressExists(anyInt());
        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> {
            accountAddressManager.add(accountId, request);
        });
    }
    @Test
    void add_ShouldThrowExceptionWhenAddressExitsInAccount() {
        // Prepare
        int accountId = 1;
        CreateAccountAddressRequest request = new CreateAccountAddressRequest(1);


        AccountAddress accountAddress = new AccountAddress(accountId);
        accountAddress.setAddressId(1);
        accountAddress.setAccount(new Account(1));
        CreatedAccountAddressResponse expectedResponse = new CreatedAccountAddressResponse(
                1,
                1,
                accountId
        );

        doNothing().when(customerClient).checkIfAddressExists(anyInt());
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account()));
        when(accountAddressRepository.existsByAccountIdAndAddressId(anyInt(), anyInt())).thenReturn(true);


        assertThrows(BusinessException.class, () -> {
            accountAddressManager.add(accountId, request);
        });
    }
    @Test
    void add_ShouldThrowExceptionWhenAddressNotBelongToAccountOwner() {
        // Prepare
        int accountId = 1;
        CreateAccountAddressRequest request = new CreateAccountAddressRequest(1);


        AccountAddress accountAddress = new AccountAddress(accountId);
        accountAddress.setAddressId(1);
        accountAddress.setAccount(new Account(1));
        CreatedAccountAddressResponse expectedResponse = new CreatedAccountAddressResponse(
                1,
                1,
                accountId
        );

        doNothing().when(customerClient).checkIfAddressExists(anyInt());
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account()));
        when(accountAddressRepository.existsByAccountIdAndAddressId(anyInt(), anyInt())).thenReturn(true);
        doThrow(BusinessException.class).when(customerClient).checkAddressAndCustomerMatch(any());


        assertThrows(BusinessException.class, () -> {
            accountAddressManager.add(accountId, request);
        });
    }

    @Test
    void add_ShouldAddGivenAccountAddressListSuccessful() {
        // Prepare
        Account account = new Account();
        account.setCustomerId(1);

        AccountAddressDto addressDto = new AccountAddressDto(1);

        List<AccountAddressDto> addressDtoList = new ArrayList<>();
        addressDtoList.add(addressDto);

        GetValidatedCustomerAddressesListItemDto listItemDto = new GetValidatedCustomerAddressesListItemDto(1);

        List<Integer> validatedAddresses = new ArrayList<>();
        validatedAddresses.add(1);

        when(customerClient.getValidatedCustomerAddresses(any())).thenReturn(List.of(listItemDto));


        // Execute
        accountAddressManager.add(addressDtoList, account);

        // Verify
        verify(accountAddressRepository, times(1)).saveAll(anyList());
    }

    @Test
    void delete_ShouldDeleteAccountAddressForSpecificIdSuccessful() {
        // Prepare
        int addressId = 1;
        int accountId = 1;
        AccountAddress accountAddress = new AccountAddress(1);
        accountAddress.setDeletedDate(null);
        Optional<AccountAddress> optionalAccountAddress = Optional.of(accountAddress);
        DeletedAcountAddressResponse expectedResponse = new DeletedAcountAddressResponse(
                1,
                LocalDateTime.now(),
                1,
                1);

        when(accountAddressRepository.findByAccountIdAndAddressId(accountId, addressId)).thenReturn(optionalAccountAddress);
        when(accountAddressRepository.save(any(AccountAddress.class))).thenReturn(optionalAccountAddress.get());
        when(accountAddressRepository.findById(anyInt())).thenReturn(Optional.of(new AccountAddress()));
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account(1)));
        doNothing().when(customerClient).checkIfAddressExists(anyInt());


        // Execute
        DeletedAcountAddressResponse response = accountAddressManager.delete(accountId, addressId);

        // Verify

        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void delete_ShouldThrowExceptionWhenDeletedDateIsNotNull() {
        // Prepare
        int addressId = 1;
        int accountId = 1;
        AccountAddress accountAddress = new AccountAddress(1);
        accountAddress.setDeletedDate(LocalDateTime.now());
        Optional<AccountAddress> optionalAccountAddress = Optional.of(accountAddress);
        DeletedAcountAddressResponse expectedResponse = new DeletedAcountAddressResponse(
                1,
                LocalDateTime.now(),
                1,
                1);

        when(accountAddressRepository.findByAccountIdAndAddressId(accountId, addressId)).thenReturn(optionalAccountAddress);
        when(accountAddressRepository.findById(anyInt())).thenReturn(Optional.of(new AccountAddress()));
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account(1)));
        doNothing().when(customerClient).checkIfAddressExists(anyInt());


        // Execute

        // Verify
        assertThrows(BusinessException.class, () -> {
            accountAddressManager.delete(accountId, addressId);
        });
    }

    @Test
    void delete_ShouldThrowExceptionWhenNotExistingAccount() {
        // Prepare
        int addressId = 1;
        int accountId = 1;
        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Execute

        // Verify
        assertThrows(NotFoundException.class, () -> {
            accountAddressManager.delete(accountId, addressId);
        });
    }

    @Test
    void delete_ShouldThrowExceptionWhenNotExistingAddress() {
        // Prepare
        int addressId = 1;
        int accountId = 1;
        AccountAddress accountAddress = new AccountAddress(1);
        accountAddress.setDeletedDate(LocalDateTime.now());
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account()));
        doThrow(NotFoundException.class).when(customerClient).checkIfAddressExists(anyInt());


        // Execute

        // Verify
        assertThrows(NotFoundException.class, () -> {
            accountAddressManager.delete(accountId, addressId);
        });
    }

    @Test
    void delete_ShouldThrowExceptionWhenNotExistingAccountAddress() {
        // Prepare
        int addressId = 1;
        int accountId = 1;
        AccountAddress accountAddress = new AccountAddress(1);
        accountAddress.setDeletedDate(LocalDateTime.now());
        Optional<AccountAddress> optionalAccountAddress = Optional.of(accountAddress);
        DeletedAcountAddressResponse expectedResponse = new DeletedAcountAddressResponse(
                1,
                LocalDateTime.now(),
                1,
                1);

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account()));
        doNothing().when(customerClient).checkIfAddressExists(anyInt());
        when(accountAddressRepository.findById(anyInt())).thenReturn(Optional.empty());


        // Execute

        // Verify
        assertThrows(NotFoundException.class, () -> {
            accountAddressManager.delete(accountId, addressId);
        });
    }

    @Test
    void getAllByAccountId_ShouldReturnAllAccountAddressesForSpecificAccountIdSuccessful() {
        // Prepare
        int accountId = 1;
        List<AccountAddress> accountAddressList = new ArrayList<>();

        when(accountAddressRepository.findAllAccountAddressesByAccountId(accountId)).thenReturn(accountAddressList);
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(new Account()));

        // Execute
        List<GetAllByAccountIdResponse> response = accountAddressManager.getAllByAccountId(accountId);

        // Verify
    }

    @Test
    void getAllByAccountId_ShouldThrowExceptionWhenNotExistingAccount() {
        // Prepare
        int accountId = 1;
        List<AccountAddress> accountAddressList = new ArrayList<>();

        when(accountAddressRepository.findAllAccountAddressesByAccountId(accountId)).thenReturn(accountAddressList);
        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Execute


        // Verify
        assertThrows(NotFoundException.class, () -> {
            accountAddressManager.getAllByAccountId(accountId);
        });
    }
}
