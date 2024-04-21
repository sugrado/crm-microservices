package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.ContactService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CustomerContactDto;
import com.turkcell.crm.customer_service.business.mappers.ContactMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.ContactRepository;
import com.turkcell.crm.customer_service.entities.concretes.Contact;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactManager implements ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public void add(CustomerContactDto customerContactDto, Customer customer) {
       Contact contact=contactMapper.toContact(customerContactDto);
       contact.setCustomer(customer);
       contactRepository.save(contact);
    }
}
