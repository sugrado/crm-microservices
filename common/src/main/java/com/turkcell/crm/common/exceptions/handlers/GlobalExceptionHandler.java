package com.turkcell.crm.common.exceptions.handlers;

import com.turkcell.crm.common.exceptions.problem_details.*;
import com.turkcell.crm.common.exceptions.types.AuthorizationException;
import com.turkcell.crm.common.exceptions.types.BusinessException;
import com.turkcell.crm.common.exceptions.types.NotFoundException;
import com.turkcell.crm.common.exceptions.types.ValidationException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundProblemDetails handleNotFoundException(NotFoundException exception) {
        NotFoundProblemDetails notFoundProblemDetails = new NotFoundProblemDetails();
        notFoundProblemDetails.setDetail(exception.getMessage());
        return notFoundProblemDetails;
    }

    @ExceptionHandler({AuthorizationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthorizationProblemDetails handleAuthorizationException() {
        return new AuthorizationProblemDetails();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        );

        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationErrors);
        return validationProblemDetails;
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationProblemDetails handleValidationException(ValidationException exception) {
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(exception.getErrors());
        return validationProblemDetails;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerProblemDetails handleException() {
        return new InternalServerProblemDetails();
    }
}