package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.abstracts.CityService;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.ChangeDefaultAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CheckAddressAndCustomerMatchRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.addresses.CreateAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.ChangedDefaultAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.CreatedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.DeletedAddressResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.addresses.GetByIdAddressResponse;
import com.turkcell.crm.customer_service.business.mappers.AddressMapper;
import com.turkcell.crm.customer_service.business.rules.AddressBusinessRules;
import com.turkcell.crm.customer_service.business.rules.CustomerBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.AddressRepository;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import com.turkcell.crm.customer_service.entities.concretes.City;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// TODO: Update address methodu yazılacak
@Service
@RequiredArgsConstructor
public class AddressManager implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CityService cityService;
    private final AddressBusinessRules addressBusinessRules;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public void add(List<AddressDto> addressDtoList, Customer customer) {

        List<Integer> cities = this.cityService
                .getAllById(addressDtoList.stream().map(AddressDto::getCityId).toList())
                .stream()
                .map(City::getId)
                .toList();
        List<Address> addressList = addressDtoList.stream().filter(a -> cities.contains(a.getCityId())).map(x -> {
            Address address = this.addressMapper.toCustomerAddress(x);
            address.setCustomer(customer);
            return address;
        }).toList();
        this.addressBusinessRules.customerShouldHaveAtLeastOneAddress(addressList);
        addressList.get(0).setDefaultAddress(true);
        this.addressRepository.saveAll(addressList);
    }

    @Override
    public GetByIdAddressResponse getById(int id) {

        Optional<Address> optionalAddress = this.addressRepository.findById(id);
        this.addressBusinessRules.addressShouldBeExist(optionalAddress);
        Address address = optionalAddress.get();

        return this.addressMapper.toGetByIdAddressResponse(address);
    }

    @Override
    public List<Address> getAllByCustomerAndIds(int customerId, List<Integer> ids) {

        return this.addressRepository.findAllByCustomerIdAndIdIsIn(customerId, ids);
    }

    @Override
    public void checkAddressAndCustomerMatch(CheckAddressAndCustomerMatchRequest checkAddressAndCustomerMatchRequest) {

        this.addressBusinessRules.addressAndCustomerShouldBeMatch(
                checkAddressAndCustomerMatchRequest.addressId(), checkAddressAndCustomerMatchRequest.customerId());
    }

    @Override
    @Transactional
    public CreatedAddressResponse add(CreateAddressRequest createAddressRequest) {

        this.customerBusinessRules.customerShouldBeExist(createAddressRequest.customerId());

        Address address = this.addressMapper.toCustomerAddressWithRequest(createAddressRequest);

        if (address.isDefaultAddress()) {
            Address defaultAddress = this.getDefault(address.getCustomer());
            this.changeState(defaultAddress, false);
        }

        return this.addressMapper.toCreateAddressResponse(this.addressRepository.save(address));
    }

    @Override
    public DeletedAddressResponse delete(int id) {

        Optional<Address> addressOptional = this.addressRepository.findById(id);
        this.addressBusinessRules.addressShouldBeExist(addressOptional);
        Address address = addressOptional.get();

        this.addressBusinessRules.defaultAddressCanNotDelete(address);
        address.setDeletedDate(LocalDateTime.now());
        Address deletedAddress = this.addressRepository.save(address);
        return this.addressMapper.toDeletedAddressResponse(deletedAddress);
    }

    @Override
    @Transactional
    public ChangedDefaultAddressResponse changeDefaultAddress(ChangeDefaultAddressRequest changeDefaultAddressRequest) {

        Optional<Address> newDefaultAddressOptional = this.addressRepository.findById(changeDefaultAddressRequest.addressId());
        this.addressBusinessRules.addressShouldBeExist(newDefaultAddressOptional);
        Address newDefaultAddress = newDefaultAddressOptional.get();

        Address oldDefaultAddress = this.getDefault(newDefaultAddress.getCustomer());
        this.changeState(oldDefaultAddress, false);

        Address updatedAddress = this.changeState(newDefaultAddress, true);
        return this.addressMapper.toChangedDefaultAddressResponse(updatedAddress);
    }

    private Address getDefault(Customer customer) {

        Optional<Address> defaultAddressOptional = this.addressRepository.findByDefaultAddressIsTrueAndCustomerId(customer.getId());
        this.addressBusinessRules.addressShouldBeExist(defaultAddressOptional);
        return defaultAddressOptional.get();
    }

    private Address changeState(Address address, boolean isDefault) {

        address.setDefaultAddress(isDefault);
        return this.addressRepository.save(address);
    }
}
