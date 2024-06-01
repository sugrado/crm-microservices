package com.turkcell.crm.common.shared.kafka.events.customers;

public class CustomerDeletedEvent {
    private int id;

    public CustomerDeletedEvent(int id) {
        this.id = id;
    }

    public CustomerDeletedEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
