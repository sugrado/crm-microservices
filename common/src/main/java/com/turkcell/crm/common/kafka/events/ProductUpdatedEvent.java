package com.turkcell.crm.common.kafka.events;

import java.time.LocalDateTime;

public class ProductUpdatedEvent {
    private int id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private String productTitle;
    private String productDescription;
    private double productPrice;
    private int productUnitInStock;
    private int propertyId;
    private String propertyName;
    private int productPropertyId;
    private String ProductPropertyValue;

    public ProductUpdatedEvent(String categoryDescription, int id, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, int categoryId, String categoryName, String productTitle, String productDescription, double productPrice, int productUnitInStock, int propertyId, String propertyName, int productPropertyId, String ProductPropertyValue) {
        this.categoryDescription = categoryDescription;
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productUnitInStock = productUnitInStock;
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.productPropertyId = productPropertyId;
        this.ProductPropertyValue = ProductPropertyValue;
    }

    public ProductUpdatedEvent() {
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductUnitInStock() {
        return productUnitInStock;
    }

    public void setProductUnitInStock(int productUnitInStock) {
        this.productUnitInStock = productUnitInStock;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getProductPropertyId() {
        return productPropertyId;
    }

    public void setProductPropertyId(int productPropertyId) {
        this.productPropertyId = productPropertyId;
    }

    public String getProductPropertyValue() {
        return ProductPropertyValue;
    }

    public void setProductPropertyValue(String ProductPropertyValue) {
        this.ProductPropertyValue = ProductPropertyValue;
    }
}

