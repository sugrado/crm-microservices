package com.turkcell.crm.order_service.api.clients;

import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accountService", path = "account-service/api/v1")
public interface AccountClient {
    @GetMapping("/accounts/{id}")
    void getByIdAccount(@PathVariable int id);

    @GetMapping("/accounts/{accountId}/addresses/{addressId}")
    GetByIdAccountAddressResponse getAccountAddress(@PathVariable int accountId, @PathVariable int addressId);
}
