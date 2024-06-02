package com.turkcell.crm.invoice_service.api.clients;

import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", path = "account-service/api/v1")
public interface AccountClient {
    @GetMapping("/accounts/{id}")
    GetByIdAccountResponse getById(@PathVariable int id);
}
