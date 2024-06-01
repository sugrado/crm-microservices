package com.turkcell.crm.search_service.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "individualCustomers")
public class IndividualCustomer {
    @Id
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalityId;
    private String mobilePhone;
}
