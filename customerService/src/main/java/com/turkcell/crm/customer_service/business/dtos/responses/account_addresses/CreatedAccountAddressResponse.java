package com.turkcell.crm.customer_service.business.dtos.responses.account_addresses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatedAccountAddressResponse {
    int id;
    int adressId;
    int acoountId;
}
