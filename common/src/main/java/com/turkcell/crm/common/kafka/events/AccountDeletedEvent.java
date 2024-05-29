package com.turkcell.crm.common.kafka.events;

public class AccountDeletedEvent {
    private int id;

    public AccountDeletedEvent(int id) {
        this.id = id;
    }
    public AccountDeletedEvent() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
