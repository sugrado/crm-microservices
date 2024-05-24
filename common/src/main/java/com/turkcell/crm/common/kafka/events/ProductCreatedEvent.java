package com.turkcell.crm.common.kafka.events;


public class ProductCreatedEvent {
    private int id;
    String title;

    String categoryName;
    int categoryId;

    public ProductCreatedEvent(int id, String title, String categoryName, int categoryId) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public ProductCreatedEvent() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}