package com.turkcell.crm.identity_service.core.utilities.exceptions.problem_details;

import com.turkcell.crm.common.exceptions.problem_details.ProblemDetails;

public class AuthenticationProblemDetails extends ProblemDetails {
    public AuthenticationProblemDetails() {
        setTitle("Authentication Error");
        setDetail("You are not authenticated.");
        setType("http://mydomain.com/exceptions/authentication");
        setStatus("401");
    }
}
