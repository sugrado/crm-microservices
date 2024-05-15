package com.turkcell.crm.account_service.core.utilities.exceptions.problem_details;

public class AuthenticationProblemDetails extends ProblemDetails {
    public AuthenticationProblemDetails() {
        setTitle("Authentication Error");
        setDetail("You are not authenticated.");
        setType("http://mydomain.com/exceptions/authentication");
        setStatus("401");
    }
}
