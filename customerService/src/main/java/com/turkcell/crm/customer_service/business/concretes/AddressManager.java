package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.abstracts.CityService;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CheckAddressCustomerCheckRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.GetByIdAddressResponse;
import com.turkcell.crm.customer_service.business.mappers.AddressMapper;
import com.turkcell.crm.customer_service.business.rules.AddressBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.AddressRepository;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import com.turkcell.crm.customer_service.entities.concretes.City;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressManager implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CityService cityService;
    private final AddressBusinessRules addressBusinessRules;

    @Override
    public void add(List<AddressDto> addressDtoList, Customer customer) {
        List<Integer> cities = cityService
                .getAllById(addressDtoList.stream().map(AddressDto::getCityId).toList())
                .stream()
                .map(City::getId)
                .toList();
        List<Address> addressList = addressDtoList.stream().filter(a -> cities.contains(a.getCityId())).map(x -> {
            Address address = addressMapper.toCustomerAddress(x);
            address.setCustomer(customer);
            return address;
        }).toList();
        addressRepository.saveAll(addressList);
    }

    @Override
    public GetByIdAddressResponse getById(int id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        addressBusinessRules.addressShouldBeExist(optionalAddress);
        Address address = optionalAddress.get();
        return addressMapper.toGetByIdAddressResponse(address);
    }

    @Override
    public List<Address> getAllByCustomerAndIds(int customerId, List<Integer> ids) {
        return this.addressRepository.findAllByCustomerIdAndIdIsIn(customerId, ids);
    }

    @Override
    public void checkAddressAndCustomerMatch(CheckAddressCustomerCheckRequest checkAddressCustomerCheckRequest) {
        addressBusinessRules.addressAndCustomerShouldBeMatch(
                checkAddressCustomerCheckRequest.addressId(), checkAddressCustomerCheckRequest.customerId());
    }
}
