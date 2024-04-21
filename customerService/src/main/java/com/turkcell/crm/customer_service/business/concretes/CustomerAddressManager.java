package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.CustomerAddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CustomerAddressDto;
import com.turkcell.crm.customer_service.business.mappers.CustomerAddressMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerAddressRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import com.turkcell.crm.customer_service.entities.concretes.CustomerAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerAddressManager implements CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerAddressMapper customerAddressMapper;

    @Override
    public void add(List<CustomerAddressDto> customerAddressDtoList, Customer customer) {
        List<CustomerAddress> customerAddressList = customerAddressDtoList.stream().map(x -> {
            CustomerAddress customerAddress = customerAddressMapper.toCustomerAddress(x);
            customerAddress.setCustomer(customer);
            return customerAddress;
        }).toList();
        customerAddressRepository.saveAll(customerAddressList);
    }
}
