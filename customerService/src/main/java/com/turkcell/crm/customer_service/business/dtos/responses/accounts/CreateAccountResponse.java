package com.turkcell.crm.customer_service.business.dtos.responses.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAccountResponse {
    private int id;
    private LocalDateTime createdDate;
    String status;
    String name;
    String number;
    int customerId;
    int accountTypeId;
}
