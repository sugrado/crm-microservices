package com.turkcell.crm.identity_service.core.utilities.exceptions.handlers;

import com.turkcell.crm.identity_service.core.utilities.exceptions.problem_details.AuthenticationProblemDetails;
import com.turkcell.crm.identity_service.core.utilities.exceptions.types.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationProblemDetails handleValidationException() {
        AuthenticationProblemDetails authenticationProblemDetails = new AuthenticationProblemDetails();
        authenticationProblemDetails.setDetail("Invalid username or password.");
        return authenticationProblemDetails;
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationProblemDetails handleAuthenticationException() {
        return new AuthenticationProblemDetails();
    }
}
