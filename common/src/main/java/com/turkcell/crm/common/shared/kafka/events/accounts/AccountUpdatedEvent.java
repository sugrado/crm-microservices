package com.turkcell.crm.common.shared.kafka.events.accounts;

public class AccountUpdatedEvent {
    private int id;
    private String status;
    private String name;
    private String number;
    private String type;
    private int accountTypeId;
    private int addressId;
    private int accountAddressId;
}
