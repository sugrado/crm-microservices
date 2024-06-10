package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.mappers.CustomerMapper;
import com.turkcell.crm.customer_service.business.mappers.CustomerMapperImpl;
import com.turkcell.crm.customer_service.business.rules.CustomerBusinessRules;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerManagerTest {

    private CustomerManager customerManager;
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private CustomerBusinessRules customerBusinessRules;
    private AddressService addressService;
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        messageService = Mockito.mock(MessageService.class);
        customerService = Mockito.mock(CustomerService.class);
        customerRepository = Mockito.mock(CustomerRepository.class);

        customerBusinessRules = Mockito.mock(CustomerBusinessRules.class);
        addressService = Mockito.mock(AddressService.class);
        CustomerMapper customerMapper = new CustomerMapperImpl();// CustomerMapper sadece burada tanımlanıyor

        customerManager = new CustomerManager(customerRepository, customerBusinessRules, customerMapper, addressService);
    }


    @Test
    void add_ShouldAddCustomer() {
        // Arrange
        List<AddressDto> addresses = List.of(new AddressDto(1, 1, "dfdfg", "City 1", "sdfdsf"));
        CreateCustomerRequest request = new CreateCustomerRequest("John", "Doe", addresses);
        Customer customer = new Customer();
        Customer createdCustomer = new Customer();

        // Mocking the behavior of customerRepository.save()
        when(customerRepository.save(any(Customer.class))).thenReturn(createdCustomer); // Mocking the behavior to return 'createdCustomer' when 'save' is called with any Customer object

        // Act
        Customer result = customerManager.add(request);

        // Assert
        assertEquals(createdCustomer, result);
    }

    @Test
    void emailShouldBeUnique_ShouldThrowBusinessException_WhenEmailExists() {
        // Arrange
        String existingEmail = "existing@example.com";
        when(customerRepository.findByEmail(existingEmail)).thenReturn(Optional.of(new Customer()));
        when(messageService.getMessage(any())).thenReturn("Email already exists.");

        CustomerBusinessRules customerBusinessRules = new CustomerBusinessRules(messageService, customerRepository);

        // Act and Assert
        assertThrows(BusinessException.class, () -> {
            customerBusinessRules.emailShouldBeUnique(existingEmail);
        });
    }

    @Test
    void update_ShouldReturnUpdatedCustomer_WhenCustomerExists() {
        // Arrange
        int customerId = 1;
        UpdateCustomerRequest updateRequest = new UpdateCustomerRequest("test@exampla.com", "05458795452");
        Customer existingCustomer = new Customer();
        existingCustomer.setId(customerId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));


        // Act
        Customer updatedCustomer = customerManager.update(customerId, updateRequest);

        // Assert
        assertEquals(existingCustomer, updatedCustomer);
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenCustomerDoesNotExist() {
        // Arrange
        int nonExistentCustomerId = 1;
        UpdateCustomerRequest updateRequest = new UpdateCustomerRequest("test@example.com", "05458795452");

        // Mocking behavior of customerRepository.findById()
        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> {
            customerManager.update(nonExistentCustomerId, updateRequest);
        });
    }

    @Test
    void checkIfCustomerExists_ShouldThrowNotFoundException_WhenCustomerDoesNotExist() {
        // Arrange
        int nonExistentCustomerId = 1;

        // Mock customerRepository.findById
        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());
        doThrow(new NotFoundException("Customer not found")).when(customerBusinessRules).customerShouldBeExist(nonExistentCustomerId);

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            customerManager.checkIfCustomerExists(nonExistentCustomerId);
        });
    }

    @Test
    void checkIfCustomerExists_ShouldNotThrowException_WhenCustomerExists() {
        // Arrange
        int existentCustomerId = 1;
        Customer customer = new Customer();
        Optional<Customer> customerOptional = Optional.of(customer);

        // Mock customerRepository.findById
        when(customerRepository.findById(existentCustomerId)).thenReturn(customerOptional);

        // Act and Assert
        assertDoesNotThrow(() -> {
            customerManager.checkIfCustomerExists(existentCustomerId);
        });
    }
}