package com.turkcell.crm.common.shared.kafka.events.accounts;

public class AccountCreatedEvent {
    private int id;
    private String status;
    private String name;
    private String number;
    private String type;
    private int accountTypeId;
    private int addressId;
    private int accountAddressId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAccountAddressId() {
        return accountAddressId;
    }

    public void setAccountAddressId(int accountAddressId) {
        this.accountAddressId = accountAddressId;
    }
}
