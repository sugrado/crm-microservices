package com.turkcell.crm.common.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.crm.common.shared.exceptions.problem_details.*;
import com.turkcell.crm.common.shared.exceptions.types.*;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        ProblemDetails message = null;
        return switch (response.status()) {
            case 400 -> {
                message = getProblemDetail(response, BusinessProblemDetails.class);
                yield new BusinessException(message.getDetail());
            }
            case 401 -> {
                message = getProblemDetail(response, AuthenticationProblemDetails.class);
                yield new AuthenticationException(message.getDetail());
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
                logger.error("Status code: {} Error: {}", response.status(), response.reason());
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
            logger.error("Status code: {} Error: {} Message: {}", response.status(), response.reason(), e.getMessage());
        }
        return message;
    }
}
