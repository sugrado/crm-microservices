package com.turkcell.crm.customer_service.business.constants.messages;

public class Messages {
    public static class CustomerMessages {
        public static final String CUSTOMER_NOT_FOUND = "customerNotFound";
        public static final String EMAIL_ALREADY_EXISTS = "emailAlreadyExists";
    }

    public static class IndividualCustomerMessages {
        public static final String NOT_FOUND = "customerNotFound";
        public static final String CUSTOMER_NATIONALITY_ID_ALREADY_EXISTS = "customerNationalityIdAlreadyExists";
        public static final String CUSTOMER_NATIONALITY_ID_NOT_VALID = "customerNationalityIdNotValid";
    }

    public static class CityMessages{
        public static final String NOT_FOUND = "cityNotFound";
    }

    public static class AddressMessages {
        public static final String NOT_FOUND = "addressNotFound";
    }

    public static class AccountMessages {
        public static final String NOT_FOUND = "accountNotFound";
    }

    public static class AccountTypeMessages {
        public static final String NOT_FOUND = "accountTypeNotFound";
    }
}
