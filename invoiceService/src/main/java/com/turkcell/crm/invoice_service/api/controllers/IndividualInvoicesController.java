package com.turkcell.crm.invoice_service.api.controllers;

import com.turkcell.crm.invoice_service.business.abstracts.IndividualInvoiceService;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetAllByAccountIdResponse;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetByOrderIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("invoice-service/api/v1/individual-invoices")
public class IndividualInvoicesController {
    private final IndividualInvoiceService individualInvoiceService;

    @GetMapping("by-order/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public GetByOrderIdResponse getByOrderId(@PathVariable int orderId) {
        return this.individualInvoiceService.getByOrderId(orderId);
    }

    @GetMapping("by-account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllByAccountIdResponse> getAllByAccountId(@PathVariable int accountId) {
        return this.individualInvoiceService.getAllByAccountId(accountId);
    }
}
