package com.turkcell.crm.gateway_service.exceptions.handlers;

import com.turkcell.crm.gateway_service.constant.Messages;
import com.turkcell.crm.gateway_service.exceptions.types.AuthenticationException;
import com.turkcell.crm.gateway_service.exceptions.types.InternalServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            logger.error(httpResponse.getStatusText(), httpResponse.getStatusCode());
            throw new InternalServerException();
        }
        if (httpResponse.getStatusCode().is4xxClientError() &&
                httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new AuthenticationException(Messages.UNAUTHENTICATED);
        }
    }
}
