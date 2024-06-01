package com.turkcell.crm.invoice_service.business.concretes;

import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.invoice_service.business.abstracts.IndividualInvoiceService;
import com.turkcell.crm.invoice_service.business.abstracts.InvoiceProductService;
import com.turkcell.crm.invoice_service.business.abstracts.InvoiceService;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetAllByAccountIdResponse;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetByOrderIdResponse;
import com.turkcell.crm.invoice_service.business.mappers.IndividualInvoiceMapper;
import com.turkcell.crm.invoice_service.business.rules.IndividualInvoiceBusinessRules;
import com.turkcell.crm.invoice_service.data_access.abstracts.IndividualInvoiceRepository;
import com.turkcell.crm.invoice_service.entities.concretes.IndividualInvoice;
import com.turkcell.crm.invoice_service.entities.concretes.Invoice;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualInvoiceManager implements IndividualInvoiceService {
    private final IndividualInvoiceRepository individualInvoiceRepository;
    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final IndividualInvoiceMapper individualInvoiceMapper;
    private final IndividualInvoiceBusinessRules individualInvoiceBusinessRules;

    @Override
    @Transactional
    public void create(IndividualOrderCreatedEvent individualOrderCreatedEvent) {
        String address = String.format("%s %s %s %s %s",
                individualOrderCreatedEvent.getAddress().districtName(),
                individualOrderCreatedEvent.getAddress().street(),
                individualOrderCreatedEvent.getAddress().houseFlatNumber(),
                individualOrderCreatedEvent.getAddress().description(),
                individualOrderCreatedEvent.getAddress().cityName()
        );

        Invoice invoice = Invoice.builder()
                .email(individualOrderCreatedEvent.getEmail())
                .totalPrice(individualOrderCreatedEvent.getTotalPrice())
                .mobilePhone(individualOrderCreatedEvent.getMobilePhone())
                .address(address)
                .accountId(individualOrderCreatedEvent.getAccountId())
                .orderId(individualOrderCreatedEvent.getOrderId())
                .build();
        Invoice createdInvoice = this.invoiceService.create(invoice);

        this.invoiceProductService.addToInvoice(createdInvoice, individualOrderCreatedEvent.getProducts());

        IndividualInvoice individualInvoice = this.individualInvoiceMapper.toIndividualInvoice(individualOrderCreatedEvent);
        individualInvoice.setInvoice(createdInvoice);
        this.individualInvoiceRepository.save(individualInvoice);
    }

    @Override
    public List<GetAllByAccountIdResponse> getAllByAccountId(int accountId) {
        List<Invoice> invoices = this.invoiceService.getAllByAccountId(accountId);
        return invoices
                .stream()
                .map(this.individualInvoiceMapper::toGetAllByAccountIdResponse)
                .toList();
    }

    @Override
    public GetByOrderIdResponse getByOrderId(int orderId) {
        Invoice invoice = this.invoiceService.getByOrderId(orderId);
        this.individualInvoiceBusinessRules.individualInvoiceShouldBeExists(invoice.getIndividual());
        return this.individualInvoiceMapper.toGetByOrderIdResponse(invoice);
    }
}
