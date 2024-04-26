package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.abstracts.CityService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.mappers.AddressMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.AddressRepository;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import com.turkcell.crm.customer_service.entities.concretes.City;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressManager implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper customerAddressMapper;
    private final CityService cityService;

    @Override
    public void add(List<AddressDto> addressDtoList, Customer customer) {
        List<Address> addressList = addressDtoList.stream().map(x -> {
            Address address = customerAddressMapper.toCustomerAddress(x);
            address.setCustomer(customer);
            City city = this.cityService.getById(x.getCityId());
            address.setCity(city);
            return address;
        }).toList();
        addressRepository.saveAll(addressList);
    }
}
