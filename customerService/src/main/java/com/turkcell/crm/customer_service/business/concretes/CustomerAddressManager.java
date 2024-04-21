package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.CustomerAddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CustomerAddressDto;
import com.turkcell.crm.customer_service.business.mappers.CustomerAddressMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerAddressManager implements CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerAddressMapper customerAddressMapper;
    @Override
    public void add(List<CustomerAddressDto> customerAddressDtoList) {
        customerAddressRepository.saveAll(customerAddressMapper.toCustomerAddresses(customerAddressDtoList));
    }
}
