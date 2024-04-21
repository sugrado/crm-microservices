package com.turkcell.crm.identityService.core.utilities.exceptions.problemDetails;

public class AuthenticationProblemDetails extends ProblemDetails {
    public AuthenticationProblemDetails() {
        setTitle("Authentication Failed");
        setType("http://mydomain.com/exceptions/authentication");
        setStatus("401");
    }
}
