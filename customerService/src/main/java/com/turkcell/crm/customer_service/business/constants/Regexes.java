package com.turkcell.crm.customer_service.business.constants;

public class Regexes {
    public static final String ALPHANUMERIC_VALIDATOR = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$";
    public static final String NUMERIC_VALIDATOR = "^(0|[1-9][0-9]*)$";
    public static final String TR_IDENTITY_VALIDATOR = "^[1-9]\\d{9}[02468]$";
}
