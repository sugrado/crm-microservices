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
@Document(collection = "accounts")
public class Account {

    @Id
    private int id;
    private String status;
    private String name;
    private String number;
    private String type;
    private int accountTypeId;
    private int addressId;
    private int accountAddressId;
}
