package com.turkcell.crm.customer_service.business.concretes;


import com.turkcell.crm.common.shared.dtos.customers.CheckAddressAndCustomerMatchRequest;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesRequest;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.customer_service.business.abstracts.CityService;
import com.turkcell.crm.customer_service.business.abstracts.DistrictService;
import com.turkcell.crm.customer_service.business.constants.Messages;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.ChangeDefaultAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.ChangedDefaultAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.CreatedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.DeletedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.GetByIdAddressResponse;
import com.turkcell.crm.customer_service.business.mappers.AddressMapper;
import com.turkcell.crm.customer_service.business.mappers.AddressMapperImpl;
import com.turkcell.crm.customer_service.business.rules.AddressBusinessRules;
import com.turkcell.crm.customer_service.business.rules.CustomerBusinessRules;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.data_access.abstracts.AddressRepository;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import com.turkcell.crm.customer_service.entities.concretes.City;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import com.turkcell.crm.customer_service.entities.concretes.District;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressManagerTest {

    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;
    private CityService cityService;
    private AddressBusinessRules addressBusinessRules;
    private CustomerBusinessRules customerBusinessRules;
    private AddressManager addressManager;
    private MessageService messageService;
    private DistrictService districtService;


    @BeforeEach
    void setUp() {
        addressRepository = mock(AddressRepository.class);
        customerRepository = mock(CustomerRepository.class);
        AddressMapper addressMapper = new AddressMapperImpl();
        cityService = mock(CityService.class);
        districtService = mock(DistrictService.class);
        messageService = mock(MessageService.class);
        addressBusinessRules = new AddressBusinessRules(messageService,addressRepository);
        customerBusinessRules = new CustomerBusinessRules(messageService, customerRepository);
        addressManager = new AddressManager(addressRepository, addressMapper, cityService, districtService, addressBusinessRules, customerBusinessRules);

    }

    @Test
    void add_shouldAddAddressesToCustomerSuccessful() {
        Customer customer = new Customer();
        customer.setId(1);

        City city = new City();
        city.setId(1);
        City city2 = new City();
        city2.setId(2);

        AddressDto addressDto = new AddressDto(1, 1,"Street", "123", "Description");
        AddressDto addressDto2 = new AddressDto(2, 1,"Street", "123", "Description");
        List<AddressDto> addressDtoList = Arrays.asList(addressDto, addressDto2);

        when(cityService.getAllById(any())).thenReturn(List.of(city, city2));

        addressManager.add(addressDtoList, customer);

        ArgumentCaptor<List<Address>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(addressRepository, times(1)).saveAll(argumentCaptor.capture());

        List<Address> capturedAddresses = argumentCaptor.getValue();
        assertEquals(2, capturedAddresses.size());
        for (Address capturedAddress : capturedAddresses) {
            assertEquals(customer, capturedAddress.getCustomer());
        }
    }

    @Test
    void add_shouldThrowExceptionWhenCustomerHasNoAddress() {
        Customer customer = new Customer();
        customer.setId(1);

        List<AddressDto> emptyAddressDtoList = Arrays.asList();

        String expectedMessage = "customerShouldHaveAtLeastOneAddress";
        when(messageService.getMessage(Messages.AddressMessages.CUSTOMER_SHOULD_HAVE_AT_LEAST_ONE_ADDRESS))
                .thenReturn(expectedMessage);

        Exception exception = assertThrows(BusinessException.class, () -> {
            addressManager.add(emptyAddressDtoList, customer);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getById_shouldReturnAddressByIdSuccessful() {
        Address address = new Address();
        address.setId(1);
        address.setStreet("Street");
        address.setHouseFlatNumber("123");
        address.setDescription("Description");
        address.setDefaultAddress(true);

        Customer customer = new Customer();
        customer.setId(1);
        address.setCustomer(customer);

        City city = new City();
        city.setId(1);
        city.setName("CityName");
        address.setCity(city);

        District district = new District();
        district.setId(1);
        district.setName("DistrictName");
        address.setDistrict(district);

        Optional<Address> optionalAddress = Optional.of(address);
        when(addressRepository.findById(1)).thenReturn(optionalAddress);

        GetByIdAddressResponse expectedResponse = new GetByIdAddressResponse(1, 1,"Street", "123", "Description", true, 1,1);

        GetByIdAddressResponse response = addressManager.getById(1);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(addressRepository, times(1)).findById(anyInt());
    }
    @Test
    void getById_shouldThrowExceptionWhenAddressIsNotExist() {
        when(addressRepository.findById(anyInt())).thenReturn(Optional.empty());

        when(messageService.getMessage(anyString())).thenReturn("addressNotFound");

        Exception exception = assertThrows(NotFoundException.class, () -> {
            addressManager.getById(1);
        });

        String expectedMessage = "addressNotFound";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(addressRepository, times(1)).findById(anyInt());
    }

    @Test
    void shouldReturnAllAddressesByCustomerAndIdsSuccessful() {
        Address address = new Address();
        address.setId(1);

        GetValidatedCustomerAddressesRequest request = new GetValidatedCustomerAddressesRequest(1, List.of(1));
        when(addressRepository.findAllByCustomerIdAndIdIsIn(anyInt(), anyList())).thenReturn(List.of(address));

        List<GetValidatedCustomerAddressesListItemDto> response = addressManager.getAllByCustomerAndIds(request);

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(addressRepository, times(1)).findAllByCustomerIdAndIdIsIn(anyInt(), anyList());
    }

    @Test
    void shouldCheckAddressAndCustomerMatchSuccessful() {
        int addressId = 1;
        int customerId = 1;
        CheckAddressAndCustomerMatchRequest request = new CheckAddressAndCustomerMatchRequest(addressId, customerId);

        when(addressRepository.existsByIdAndCustomerId(request.addressId(), request.customerId())).thenReturn(true);

        assertDoesNotThrow(() -> addressManager.checkAddressAndCustomerMatch(request));
    }

    @Test
    @Transactional
    void add_shouldAddAddressSuccessful() {
        Address address = new Address();
        address.setId(1);
        Customer customer = new Customer();
        customer.setId(1);
        address.setCustomer(customer);
        CreateAddressRequest createAddressRequest = new CreateAddressRequest(1, 1, 1,"Street", "123", "Description", false);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        CreatedAddressResponse expectedResponse = new CreatedAddressResponse(1, "Street", "123", "Description", false, 1,1);
        CreatedAddressResponse response = addressManager.add(createAddressRequest);

        assertNotNull(response);
        assertEquals(expectedResponse.id(), response.id());
    }

    @Test
    void add_shouldThrowExceptionWhenCustomerIsNotExist() {
        CreateAddressRequest createAddressRequest = new CreateAddressRequest(1, 1, 1,"Street", "123", "Description", false);

        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.CustomerMessages.NOT_FOUND)).thenReturn("customerNotFound");

        Exception exception = assertThrows(NotFoundException.class, () -> {
            addressManager.add(createAddressRequest);
        });

        String expectedMessage = "customerNotFound";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete_shouldDeleteAddressSuccessful() {
        Address address = new Address();
        City city = new City();
        city.setId(1);
        address.setId(1);
        address.setCity(city);
        Optional<Address> optionalAddress = Optional.of(address);

        when(addressRepository.findById(anyInt())).thenReturn(optionalAddress);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        DeletedAddressResponse expectedResponse = new DeletedAddressResponse(1, LocalDateTime.now(), "Street", "123", "Description", false, 1,1);

        DeletedAddressResponse response = addressManager.delete(address.getId());

        assertNotNull(response);
        assertEquals(expectedResponse.id(), response.id());
        assertEquals(expectedResponse.cityId(), response.cityId());
    }

    @Test
    @Transactional
    void shouldChangeDefaultAddressSuccessful() {
        Customer customer = new Customer();
        customer.setId(1);

        Address oldAddress = new Address();
        oldAddress.setId(1);
        oldAddress.setCustomer(customer);
        oldAddress.setDefaultAddress(true);

        Address newAddress = new Address();
        newAddress.setId(2);
        newAddress.setCustomer(customer);
        newAddress.setDefaultAddress(false);
        newAddress.setStreet("Street");
        newAddress.setHouseFlatNumber("123");
        newAddress.setDescription("Description");
        City city = new City();
        city.setId(1);
        newAddress.setCity(city);

        ChangeDefaultAddressRequest changeDefaultAddressRequest = new ChangeDefaultAddressRequest(2);

        when(addressRepository.findByDefaultAddressIsTrueAndCustomerId(customer.getId())).thenReturn(Optional.of(oldAddress));
        when(addressRepository.findById(changeDefaultAddressRequest.addressId())).thenReturn(Optional.of(newAddress));
        when(addressRepository.save(any(Address.class))).thenReturn(newAddress);

        ChangedDefaultAddressResponse expectedResponse = new ChangedDefaultAddressResponse(2, "Street", "123", "Description", true, 1,1);

        ChangedDefaultAddressResponse result = addressManager.changeDefaultAddress(changeDefaultAddressRequest);

        assertNotNull(result);
        assertEquals(expectedResponse.id(), result.id());
        assertEquals(expectedResponse.street(), result.street());
        assertEquals(expectedResponse.houseFlatNumber(), result.houseFlatNumber());
        assertEquals(expectedResponse.description(), result.description());
        assertEquals(expectedResponse.defaultAddress(), result.defaultAddress());
        assertEquals(expectedResponse.cityId(), result.cityId());
        assertFalse(oldAddress.isDefaultAddress());
        assertTrue(newAddress.isDefaultAddress());
    }

}
