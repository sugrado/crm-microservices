package com.turkcell.crm.account_service.core.utilities.exceptions.problem_details;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails {
    public ValidationProblemDetails() {
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setType("http://mydomain.com/exceptions/validation");
        setStatus("422");
    }

    private Map<String, String> errors;
}