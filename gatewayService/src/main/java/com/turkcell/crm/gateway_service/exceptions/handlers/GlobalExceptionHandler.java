package com.turkcell.crm.gateway_service.exceptions.handlers;

import com.turkcell.crm.gateway_service.exceptions.problem_details.AuthenticationProblemDetails;
import com.turkcell.crm.gateway_service.exceptions.problem_details.InternalServerProblemDetails;
import com.turkcell.crm.gateway_service.exceptions.types.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationProblemDetails handleAuthenticationException(AuthenticationException ex) {
        AuthenticationProblemDetails authenticationProblemDetails = new AuthenticationProblemDetails();
        authenticationProblemDetails.setDetail(ex.getMessage());
        return authenticationProblemDetails;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerProblemDetails handleException(Exception ex) {
        logger.error(ex.getMessage());
        return new InternalServerProblemDetails();
    }
}
