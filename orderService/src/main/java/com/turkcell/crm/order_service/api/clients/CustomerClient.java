package com.turkcell.crm.order_service.api.clients;

import com.turkcell.crm.common.shared.dtos.customers.GetIndividualCustomerInvoiceInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", path = "customer-service/api/v1")
public interface CustomerClient {
    @GetMapping("/individual-customers/invoice-info-by-address/{addressId}")
    GetIndividualCustomerInvoiceInfoDto getInvoiceInfoByAddress(@PathVariable int addressId);
}
