package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class CreateCustomerRequest {
//    @NotNull
//    @Size(min = 1)
//    private String firstName;
//    @Size(min = 1)
//    private String middleName;
//    @NotNull
//    @Size(min = 1)
//    private String lastName;
//    @NotNull
//    @Size(min = 11, max = 11)
//    private String nationalityId;
//    @NotNull
//    private LocalDate birthDate;
//    @Size(min = 1)
//    private String motherName;
//    @Size(min = 1)
//    private String fatherName;
//    @NotNull
//    private Gender gender;
//}

public record CreateCustomerRequest(
        @NotNull
        @Size(min = 1)
        String firstName,
        @Size(min = 0)
        String middleName,
        @NotNull
        @Size(min = 1)
        String lastName,
        @NotNull
        @Size(min = 11, max = 11)
        String nationalityId,
        @NotNull
        LocalDate birthDate,
        @Size(min = 1)
        String motherName,
        @Size(min = 1)
        String fatherName,
        @NotNull
        Gender gender,
        @Valid
        List<CustomerAddressDto> customerAddresses,
        @Valid
        CustomerContactDto customerContact
) {
}