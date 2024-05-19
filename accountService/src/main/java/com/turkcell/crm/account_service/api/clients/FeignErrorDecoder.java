package com.turkcell.crm.account_service.api.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.crm.common.exceptions.problem_details.*;
import com.turkcell.crm.common.exceptions.types.*;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ProblemDetails message = null;
        return switch (response.status()) {
            case 400 -> {
                message = getProblemDetail(response, BusinessProblemDetails.class);
                yield new BusinessException(message.getDetail());
            }
            case 403 -> {
                message = getProblemDetail(response, AuthorizationProblemDetails.class);
                yield new AuthorizationException(message.getDetail());
            }
            case 404 -> {
                message = getProblemDetail(response, NotFoundProblemDetails.class);
                yield new NotFoundException(message.getDetail());
            }
            case 422 -> {
                message = getProblemDetail(response, ValidationProblemDetails.class);
                yield new ValidationException(message.getDetail(), ((ValidationProblemDetails) message).getErrors());
            }
            case 500 -> {
                message = getProblemDetail(response, InternalServerProblemDetails.class);
                yield new InternalServerException(message.getDetail());
            }
            default -> {
                log.error(String.format("Status code: %d Error: %s", response.status(), response.reason()));
                yield errorDecoder.decode(methodKey, response);
            }
        };
    }

    public <T> T getProblemDetail(Response response, Class<T> detailType) {
        T message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, detailType);
        } catch (IOException e) {
            log.error(String.format("Status code: %d Error: %s Message: %s", response.status(), response.reason(), e.getMessage()));
        }
        return message;
    }
}
