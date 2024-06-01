package com.turkcell.crm.common.shared.kafka.events.customers;

public class IndividualCustomerDeletedEvent {
    private int id;

    public IndividualCustomerDeletedEvent(int id) {
        this.id = id;
    }

    public IndividualCustomerDeletedEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
