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
@Document(collection = "individualOrders")
public class IndividualOrder {
    @Id
    private int id;
    private int accountId;
    private double totalPrice;
    private String firstName;
    private String lastName;
}
