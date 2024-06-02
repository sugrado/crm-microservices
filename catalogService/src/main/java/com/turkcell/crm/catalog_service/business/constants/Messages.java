package com.turkcell.crm.catalog_service.business.constants;

public class Messages {
    public static class ProductMessages {
        public static final String NOT_FOUND = "productNotFound";
    }

    public static class PropertyMessages {
        public static final String NOT_FOUND = "propertyNotFound";
        public static final String ALREADY_EXISTS = "propertyAlreadyExists";
    }

    public static class ProductPropertyMessages {
        public static final String ALREADY_EXISTS = "productPropertyAlreadyExists";
        public static final String NOT_FOUND = "productPropertyNotFound";

        public static final String PRODUCT_AND_PROPERTY_CATEGORY_ID_SHOULD_BE_MATCH = "productAndPropertyCategoryIdShouldBeMatch";
    }

    public static class CategoryMessages {
        public static final String NOT_FOUND = "categoryNotFound";
        public static final String ALREADY_EXISTS = "categoryAlreadyExists";
    }
}
