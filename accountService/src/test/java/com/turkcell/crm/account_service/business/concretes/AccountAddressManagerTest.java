package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.account_service.business.mappers.AccountAddressMapper;
import com.turkcell.crm.account_service.data_access.abstracts.AccountAddressRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import com.turkcell.crm.common.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.dtos.customers.GetValidatedCustomerAddressesRequest;
import com.turkcell.crm.account_service.business.rules.AccountAddressBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
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

class AccountAddressManagerTest {

    @Mock
    private AccountAddressRepository accountAddressRepository;

    @Mock
    private AccountAddressMapper accountAddressMapper;

    @Mock
    private AccountAddressBusinessRules accountAddressBusinessRules;

    @Mock
    private AccountBusinessRules accountBusinessRules;

    @Mock
    private CustomerClient customerClient;

    @InjectMocks
    private AccountAddressManager accountAddressManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void add_ShouldAddAccountAddress() {
        // Prepare
        int accountId = 1;
        CreateAccountAddressRequest request = new CreateAccountAddressRequest(1);


        AccountAddress accountAddress = new AccountAddress(accountId);
        CreatedAccountAddressResponse expectedResponse = new CreatedAccountAddressResponse(
                1,
                1,
                accountId
        );

        when(accountAddressMapper.toAccountAddress(request)).thenReturn(accountAddress);
        when(accountAddressRepository.save(accountAddress)).thenReturn(accountAddress);
        when(accountAddressMapper.toCreatedAccountAddressResponse(accountAddress)).thenReturn(expectedResponse);

        // Execute
        CreatedAccountAddressResponse response = accountAddressManager.add(accountId, request);

        // Verify
        verify(accountAddressBusinessRules).addressShouldBeExist(request.addressId());
        verify(accountBusinessRules).accountShouldBeExist(accountId);
        verify(accountAddressBusinessRules).addressShouldNotBeExistInAccount(accountId, request.addressId());
        verify(accountAddressBusinessRules).addressMustBelongToAccountOwner(accountId, request.addressId());
        verify(accountAddressRepository).save(accountAddress);
        assertEquals(expectedResponse, response);
    }

    @Test
    void add_ShouldAddGivenAccountAddressList() {
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
        when(accountAddressMapper.toAccountAddress(addressDto)).thenReturn(new AccountAddress());

        // Execute
        accountAddressManager.add(addressDtoList, account);

        // Verify
        verify(accountAddressRepository, times(1)).saveAll(anyList());
    }

    @Test
    void delete_ShouldDeleteAccountAddressForSpecificId() {
        // Prepare
        int addressId = 1;
        AccountAddress accountAddress = new AccountAddress();
        accountAddress.setDeletedDate(null);
        Optional<AccountAddress> optionalAccountAddress = Optional.of(accountAddress);
        DeletedAcountAddressResponse expectedResponse = new DeletedAcountAddressResponse(
                1,
                LocalDateTime.now(),
                1,
                1);

        when(accountAddressRepository.findById(addressId)).thenReturn(optionalAccountAddress);
        when(accountAddressMapper.toDeletedAcountAddressResponse(accountAddress)).thenReturn(expectedResponse);

        // Execute
        DeletedAcountAddressResponse response = accountAddressManager.delete(addressId);

        // Verify
        verify(accountAddressBusinessRules).accountAddressShouldBeExist(optionalAccountAddress);
        verify(accountAddressBusinessRules).accountAddressShouldBeNotDeleted(optionalAccountAddress);
        verify(accountAddressRepository).save(accountAddress);
        assertEquals(expectedResponse, response);
    }

    @Test
    void getAllByAccountId_ShouldReturnAllAccountAddressesForSpecificAccountId() {
        // Prepare
        int accountId = 1;
        List<AccountAddress> accountAddressList = new ArrayList<>();
        when(accountAddressRepository.findAllAccountAddressesByAccountId(accountId)).thenReturn(accountAddressList);

        // Execute
        List<GetAllByAccountIdResponse> response = accountAddressManager.getAllByAccountId(accountId);

        // Verify
        verify(accountBusinessRules).accountShouldBeExist(accountId);
        verify(accountAddressMapper).toGetAllByAccountIdResponse(accountAddressList);
    }
}
