package com.turkcell.crm.common.shared.kafka.events.orders;

import java.util.List;

public class IndividualOrderCreatedEvent {
    private int orderId;
    private int accountId;
    private double totalPrice;
    private String firstName;
    private String lastName;
    private String email;
    private String mobilePhone;
    private String nationalityId;
    private OrderCreatedEventAddress address;
    private List<OrderCreatedEventProduct> products;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public OrderCreatedEventAddress getAddress() {
        return address;
    }

    public void setAddress(OrderCreatedEventAddress address) {
        this.address = address;
    }

    public List<OrderCreatedEventProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderCreatedEventProduct> products) {
        this.products = products;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}