package com.turkcell.crm.customer_service.adapters.mernis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckNationalityDTO {
    private String nationalityId;
    private String firstName;
    private String lastName;
    private int birthYear;
}
