package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.common.shared.dtos.customers.GetIndividualCustomerInvoiceInfoDto;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.CreateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.UpdateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.individual_customers.*;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request);

    List<GetAllIndividualCustomersResponse> getAll();

    GetByIdIndividualCustomerResponse getById(int id);

    UpdatedIndividualCustomerResponse update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

    DeletedIndividualCustomerResponse delete(int id);

    GetIndividualCustomerInvoiceInfoDto getInvoiceInfoByAddress(int addressId);
}