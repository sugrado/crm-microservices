package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.common.shared.dtos.customers.GetIndividualCustomerInvoiceInfoDto;
import com.turkcell.crm.common.shared.dtos.customers.GetInvoiceInfoByAddressDto;
import com.turkcell.crm.common.shared.kafka.events.customers.CustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.CustomerDeletedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.CustomerUpdatedEvent;
import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.abstracts.IndividualCustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.CreateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.UpdateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.individual_customers.*;
import com.turkcell.crm.customer_service.business.mappers.IndividualCustomerMapper;
import com.turkcell.crm.customer_service.business.rules.IndividualCustomerBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.IndividualCustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import com.turkcell.crm.customer_service.entities.concretes.IndividualCustomer;
import com.turkcell.crm.customer_service.kafka.producers.CustomerProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final IndividualCustomerMapper individualCustomerMapper;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final CustomerProducer customerProducer;

    @Override
    @Transactional
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {

        IndividualCustomer individualCustomer = individualCustomerMapper.toIndividualCustomer(request);
        individualCustomerBusinessRules.nationalityIdShouldBeUnique(individualCustomer.getNationalityId());
        individualCustomerBusinessRules.nationalityIdShouldBeValid(individualCustomer);

        Customer customer = customerService.add(request.customer());
        individualCustomer.setCustomer(customer);
        IndividualCustomer createdIndividualCustomer = this.individualCustomerRepository.save(individualCustomer);

        CustomerCreatedEvent customerCreatedEvent = individualCustomerMapper.toCustomerCreatedEvent(createdIndividualCustomer);
        customerProducer.send(customerCreatedEvent);

        return individualCustomerMapper.toCreatedIndividualCustomerResponse(createdIndividualCustomer);
    }

    @Override
    public List<GetAllIndividualCustomersResponse> getAll() {

        List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll();
        return individualCustomerMapper.toGetAllIndividualCustomersResponseList(individualCustomers);
    }

    @Override
    public GetByIdIndividualCustomerResponse getById(int id) {

        Optional<IndividualCustomer> individualCustomerOptional = this.individualCustomerRepository.findById(id);
        this.individualCustomerBusinessRules.individualCustomerShouldBeExist(individualCustomerOptional);

        IndividualCustomer individualCustomer = individualCustomerOptional.get();
        return individualCustomerMapper.toGetByIdIndividualCustomerResponse(individualCustomer);
    }

    @Override
    @Transactional
    public UpdatedIndividualCustomerResponse update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {

        Optional<IndividualCustomer> individualCustomerOptional = this.individualCustomerRepository.findById(id);
        this.individualCustomerBusinessRules.individualCustomerShouldBeExist(individualCustomerOptional);
        IndividualCustomer individualCustomer = individualCustomerOptional.get();

        individualCustomerMapper.updateIndividualCustomerFromRequest(updateIndividualCustomerRequest, individualCustomer);
        this.individualCustomerRepository.save(individualCustomer);

        CustomerUpdatedEvent customerUpdatedEvent = individualCustomerMapper.toCustomerUpdatedEvent(individualCustomer);
        customerProducer.send(customerUpdatedEvent);

        return individualCustomerMapper.toUpdatedIndividualCustomerResponse(individualCustomer);
    }

    @Override
    @Transactional
    public DeletedIndividualCustomerResponse delete(int id) {

        Optional<IndividualCustomer> individualCustomerOptional = this.individualCustomerRepository.findById(id);
        this.individualCustomerBusinessRules.individualCustomerShouldBeExist(individualCustomerOptional);

        IndividualCustomer individualCustomerToDelete = individualCustomerOptional.get();
        individualCustomerToDelete.setDeletedDate(LocalDateTime.now());
        IndividualCustomer deletedCustomer = this.individualCustomerRepository.save(individualCustomerToDelete);

        CustomerDeletedEvent customerDeletedEvent = individualCustomerMapper.toCustomerDeletedEvent(deletedCustomer);
        this.customerProducer.send(customerDeletedEvent);

        return individualCustomerMapper.toDeletedIndividualCustomerResponse(individualCustomerToDelete);
    }

    @Override
    public GetIndividualCustomerInvoiceInfoDto getInvoiceInfoByAddress(int addressId) {

        Address address = this.addressService.getByIdEntity(addressId);
        IndividualCustomer individualCustomer = address.getCustomer().getIndividualCustomer();

        GetIndividualCustomerInvoiceInfoDto invoiceInfo = individualCustomerMapper.toGetIndividualCustomerInvoiceInfoDto(individualCustomer);
        invoiceInfo.setAddress(new GetInvoiceInfoByAddressDto(address.getStreet(),
                address.getHouseFlatNumber(), address.getDescription(), address.getCity().getName(), address.getDistrict().getName()));

        return invoiceInfo;
    }
}