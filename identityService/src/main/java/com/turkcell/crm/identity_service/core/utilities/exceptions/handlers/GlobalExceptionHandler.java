package com.turkcell.crm.identity_service.core.utilities.exceptions.handlers;

import com.turkcell.crm.identity_service.core.utilities.exceptions.problemDetails.AuthenticationProblemDetails;
import com.turkcell.crm.identity_service.core.utilities.exceptions.problemDetails.BusinessProblemDetails;
import com.turkcell.crm.identity_service.core.utilities.exceptions.problemDetails.ValidationProblemDetails;
import com.turkcell.crm.identity_service.core.utilities.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {
        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        );

        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationErrors);
        return validationProblemDetails;
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationProblemDetails handleValidationException() {
        AuthenticationProblemDetails authenticationProblemDetails = new AuthenticationProblemDetails();
        authenticationProblemDetails.setDetail("Invalid username or password.");
        return authenticationProblemDetails;
    }
}
