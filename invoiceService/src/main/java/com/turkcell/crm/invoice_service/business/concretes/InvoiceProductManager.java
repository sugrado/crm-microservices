package com.turkcell.crm.invoice_service.business.concretes;

import com.turkcell.crm.common.shared.kafka.events.orders.OrderCreatedEventProduct;
import com.turkcell.crm.invoice_service.business.abstracts.InvoiceProductService;
import com.turkcell.crm.invoice_service.business.mappers.InvoiceProductMapper;
import com.turkcell.crm.invoice_service.data_access.abstracts.InvoiceProductRepository;
import com.turkcell.crm.invoice_service.entities.concretes.Invoice;
import com.turkcell.crm.invoice_service.entities.concretes.InvoiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceProductManager implements InvoiceProductService {
    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceProductMapper invoiceProductMapper;

    @Override
    public List<InvoiceProduct> addToInvoice(Invoice invoice, List<OrderCreatedEventProduct> products) {
        List<InvoiceProduct> invoiceProductsToCreate = this.invoiceProductMapper.toInvoiceProducts(products);
        invoiceProductsToCreate.forEach(invoiceProduct -> invoiceProduct.setInvoice(invoice));
        return this.invoiceProductRepository.saveAll(invoiceProductsToCreate);
    }
}
