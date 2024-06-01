package com.turkcell.crm.common.shared.kafka.events.catalogs;

public class ProductDeletedEvent {
    private int id;

    public ProductDeletedEvent(int id) {
        this.id = id;
    }

    public ProductDeletedEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
