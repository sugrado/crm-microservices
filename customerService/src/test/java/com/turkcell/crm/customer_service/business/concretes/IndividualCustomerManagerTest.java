package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.common.kafka.events.CustomerCreatedEvent;
import com.turkcell.crm.common.kafka.events.CustomerDeletedEvent;
import com.turkcell.crm.common.kafka.events.CustomerUpdatedEvent;
import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.CreateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.UpdateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.CustomerDto;
import com.turkcell.crm.customer_service.business.dtos.responses.individual_customers.*;
import com.turkcell.crm.customer_service.business.mappers.IndividualCustomerMapper;
import com.turkcell.crm.customer_service.business.rules.IndividualCustomerBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.IndividualCustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import com.turkcell.crm.customer_service.entities.concretes.IndividualCustomer;
import com.turkcell.crm.customer_service.entities.enums.Gender;
import com.turkcell.crm.customer_service.kafka.producers.CustomerProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndividualCustomerManagerTest {

    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @Mock
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;

    @Mock
    private IndividualCustomerMapper individualCustomerMapper;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerProducer customerProducer;

    @InjectMocks
    private IndividualCustomerManager individualCustomerManager;

    private CreateIndividualCustomerRequest createIndividualCustomerRequest;
    private UpdateIndividualCustomerRequest updateIndividualCustomerRequest;
    private IndividualCustomer individualCustomer;
    private Customer customer;
    private CustomerDto customerDto;
    @BeforeEach
    void setUp() {

        individualCustomer = new IndividualCustomer();
        customer = new Customer();
        customerDto = new CustomerDto("test@test.com","1234567891023");
    }

    @Test
    void add_ShouldAddIndividualCustomer() {
        // Arrange
        AddressDto addressDto = new AddressDto(3,"test street", "546","test at test at test");
        List<AddressDto> addressDtoList = new ArrayList<>() ;
        addressDtoList.add(addressDto);
        CreateCustomerRequest createCustomerRequest=new CreateCustomerRequest("test@test.com","1234567891023", addressDtoList);
        createIndividualCustomerRequest = new CreateIndividualCustomerRequest("Engin",
                null,
                "Demiroğ",
                "12345678910",
                LocalDate.of(2001,1,20),
                "Aaaaaa",
                "dsgafsdsfg",
                Gender.MALE,
                createCustomerRequest
                );
        when(individualCustomerMapper.toIndividualCustomer(any(CreateIndividualCustomerRequest.class)))
                .thenReturn(individualCustomer);
        when(customerService.add(any())).thenReturn(customer);
        when(individualCustomerRepository.save(any(IndividualCustomer.class))).thenReturn(individualCustomer);
        when(individualCustomerMapper.toCustomerCreatedEvent(any(IndividualCustomer.class)))
                .thenReturn(new CustomerCreatedEvent());
        when(individualCustomerMapper.toCreatedIndividualCustomerResponse(any(IndividualCustomer.class)))
                .thenReturn(new CreatedIndividualCustomerResponse(1, LocalDateTime.now(),"Engin",
                        null,
                        "Demiroğ",
                        null,
                        "12345678910",
                        LocalDate.of(2001,1,20),
                        "Aaaaaa",
                        "dsgafsdsfg",
                        Gender.MALE,
                        customerDto));

        // Act
        CreatedIndividualCustomerResponse response = individualCustomerManager.add(createIndividualCustomerRequest);

        // Assert
        assertNotNull(response);
        verify(individualCustomerBusinessRules).nationalityIdShouldBeUnique(any());
        verify(individualCustomerBusinessRules).nationalityIdShouldBeValid(any());
        verify(individualCustomerRepository).save(any(IndividualCustomer.class));
        verify(customerProducer).send(any(CustomerCreatedEvent.class));
    }

//    @Test
//    void getAll_ShouldReturnAllIndividualCustomers() {
//
//        // Arrange
//        List<IndividualCustomer> individualCustomers = List.of(individualCustomer);
//        when(individualCustomerRepository.findAll()).thenReturn(individualCustomers);
//        when(individualCustomerMapper.toGetAllIndividualCustomersResponseList(anyList()))
//                .thenReturn(List.of(any(GetAllIndividualCustomersResponse.class)));
//
//        // Act
//        List<GetAllIndividualCustomersResponse> response = individualCustomerManager.getAll();
//
//        // Assert
//        assertNotNull(response);
//        assertFalse(response.isEmpty());
//        verify(individualCustomerRepository).findAll();
//    }

//    @Test
//    void getById_ShouldReturnIndividualCustomer() {
//        // Arrange
//        when(individualCustomerRepository.findById(anyInt())).thenReturn(Optional.of(individualCustomer));
//        when(individualCustomerMapper.toGetByIdIndividualCustomerResponse(any(IndividualCustomer.class)))
//                .thenReturn(any(GetByIdIndividualCustomerResponse.class));
//
//        // Act
//        GetByIdIndividualCustomerResponse response = individualCustomerManager.getById(1);
//
//        // Assert
//        assertNotNull(response);
//        verify(individualCustomerBusinessRules).individualCustomerShouldBeExist(any());
//    }

    @Test
    void update_ShouldUpdateIndividualCustomer() {
        UpdateCustomerRequest updateCustomerRequest=new UpdateCustomerRequest("test@test.com","1234567891023");
        updateIndividualCustomerRequest = new UpdateIndividualCustomerRequest("Engin",
                null,
                "Demiroğ",
                LocalDate.of(2001,1,20),
                "sdasdsa",
                "Aaaaaa",
                Gender.MALE,
                updateCustomerRequest
        );
        // Arrange
        when(individualCustomerRepository.findById(anyInt())).thenReturn(Optional.of(individualCustomer));
        when(individualCustomerMapper.toCustomerUpdatedEvent(any(IndividualCustomer.class)))
                .thenReturn(new CustomerUpdatedEvent());
        when(individualCustomerMapper.toUpdatedIndividualCustomerResponse(any(IndividualCustomer.class)))
                .thenReturn(new UpdatedIndividualCustomerResponse(1,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Engin",
                        null,
                        "Demiroğ",
                        null,
                        "12345678910",
                        LocalDate.of(2001,1,20),
                        "sdasdsa",
                        "Aaaaaa",
                        Gender.MALE,
                        customerDto
                        ));

        // Act
        UpdatedIndividualCustomerResponse response = individualCustomerManager.update(1, updateIndividualCustomerRequest);

        // Assert
        assertNotNull(response);
        verify(individualCustomerRepository).save(any(IndividualCustomer.class));
        verify(customerProducer).send(any(CustomerUpdatedEvent.class));
    }
    @Test
    void delete_ShouldDeleteIndividualCustomer() {
        // Arrange
        CustomerDto customerDto = new CustomerDto("test@test.com", "1234567891023");
        IndividualCustomer individualCustomer = new IndividualCustomer();
        individualCustomer.setCustomer(new Customer());

        // Mock repository findById method
        when(individualCustomerRepository.findById(anyInt())).thenReturn(Optional.of(individualCustomer));

        // Mock mapper methods
        doReturn(new CustomerDeletedEvent()).when(individualCustomerMapper).toCustomerDeletedEvent(any(IndividualCustomer.class));
        doReturn(new DeletedIndividualCustomerResponse(
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Engin",
                null,
                "Demiroğ",
                null,
                "12345678910",
                LocalDate.of(2001, 1, 20),
                "sdasdsa",
                "asfdsadd",
                Gender.MALE,
                customerDto
        )).when(individualCustomerMapper).toDeletedIndividualCustomerResponse(any(IndividualCustomer.class));

        // Act
        DeletedIndividualCustomerResponse response = individualCustomerManager.delete(1);

        // Assert
        assertNotNull(response);
        verify(individualCustomerRepository).save(individualCustomer);
        verify(customerProducer).send(any(CustomerDeletedEvent.class));
    }


//    @Test
//    void delete_ShouldDeleteIndividualCustomer() {
//
//        // Arrange
//        when(individualCustomerRepository.findById(anyInt())).thenReturn(Optional.of(individualCustomer));
//        when(individualCustomerMapper.toCustomerDeletedEvent(any(IndividualCustomer.class)))
//                .thenReturn(new CustomerDeletedEvent());
//        when(individualCustomerMapper.toDeletedIndividualCustomerResponse(any(IndividualCustomer.class)))
//                .thenReturn(new DeletedIndividualCustomerResponse(
//                        1,
//                        LocalDateTime.now(),
//                        LocalDateTime.now(),
//                        LocalDateTime.now(),
//                        "Engin",
//                        null,
//                        "Demiroğ",
//                        null,
//                        "12345678910",
//                        LocalDate.of(2001,1,20),
//                        "sdasdsa",
//                        "asfdsadd",
//                        Gender.MALE,
//                        customerDto
//                        ));
//
//        // Act
//        DeletedIndividualCustomerResponse response = individualCustomerManager.delete(1);
//
//        // Assert
//        assertNotNull(response);
//        verify(individualCustomerRepository).save(any(IndividualCustomer.class));
//        verify(customerProducer).send(any(CustomerDeletedEvent.class));
//    }
}
