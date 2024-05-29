package com.turkcell.crm.gateway_service.exceptions.problem_details;

public class AuthenticationProblemDetails extends ProblemDetails {
    public AuthenticationProblemDetails() {
        setTitle("Authentication error");
        setType("http://mydomain.com/exceptions/authentication");
        setStatus("401");
    }
}
